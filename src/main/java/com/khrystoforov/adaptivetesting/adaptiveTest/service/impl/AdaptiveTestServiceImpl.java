package com.khrystoforov.adaptivetesting.adaptiveTest.service.impl;

import com.khrystoforov.adaptivetesting.adaptiveTest.service.AdaptiveTestService;
import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import com.khrystoforov.adaptivetesting.exception.EntityExistsException;
import com.khrystoforov.adaptivetesting.exception.EntityNotFoundException;
import com.khrystoforov.adaptivetesting.exception.MaxQuestionsAnsweredException;
import com.khrystoforov.adaptivetesting.question.dto.response.QuestionResponseDto;
import com.khrystoforov.adaptivetesting.question.mapper.QuestionMapper;
import com.khrystoforov.adaptivetesting.question.model.Question;
import com.khrystoforov.adaptivetesting.question.service.QuestionService;
import com.khrystoforov.adaptivetesting.session.dto.response.FinalScoreResponseDto;
import com.khrystoforov.adaptivetesting.session.dto.response.SessionResponseDto;
import com.khrystoforov.adaptivetesting.session.mapper.SessionMapper;
import com.khrystoforov.adaptivetesting.session.model.TestSession;
import com.khrystoforov.adaptivetesting.session.service.TestSessionService;
import com.khrystoforov.adaptivetesting.topic.model.Topic;
import com.khrystoforov.adaptivetesting.topic.service.TopicService;
import com.khrystoforov.adaptivetesting.user.User;
import com.khrystoforov.adaptivetesting.user.service.UserService;
import com.khrystoforov.adaptivetesting.userAnswer.dto.request.UserAnswerRequestDto;
import com.khrystoforov.adaptivetesting.userAnswer.dto.response.UserAnswerResponseDto;
import com.khrystoforov.adaptivetesting.userAnswer.model.UserAnswer;
import com.khrystoforov.adaptivetesting.userAnswer.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdaptiveTestServiceImpl implements AdaptiveTestService {

    private final UserService userService;
    private final TopicService topicService;
    private final TestSessionService testSessionService;
    private final QuestionService questionService;
    private final UserAnswerService userAnswerService;

    @Override
    public SessionResponseDto startTest(Long userId, String topicName) {
        if (testSessionService.isSessionExistsByTopicNameAndUserId(topicName, userId)) {
            throw new EntityExistsException("Test has already started for topic " + topicName);
        }

        User user = userService.getById(userId);
        Topic topic = topicService.getByName(topicName);
        TestSession testSession = new TestSession(user, topic);
        testSession = testSessionService.saveSession(testSession);
        return new SessionResponseDto(testSession.getId());
    }

    @Override
    public QuestionResponseDto selectNextQuestion(Long userId, UUID sessionId) {
        TestSession session = testSessionService.getSessionByIdAndUserId(sessionId, userId);
        // TODO add question count to topic
        if (session.getAnswers().size() == 10) {
            throw new MaxQuestionsAnsweredException(String.format("User %d answered all questions", userId));
        }

        BigDecimal theta = session.getCurrentTheta();
        return QuestionMapper.toResponseDto(questionService.getWithMinDifficultyDifference(theta));
    }

    @Override
    public UserAnswerResponseDto submitAnswer(Long userId, UUID sessionId, UserAnswerRequestDto userResponse) {
        TestSession session = testSessionService.getSessionByIdAndUserId(sessionId, userId);
        Question question = questionService.getQuestionByIdAndTopicId(userResponse.getQuestionId(),
                session.getTopic().getId());
        AnswerOption answerOption = question.getOptions().stream()
                .filter(o -> userResponse.getResponse().equals(o.getText()))
                .findFirst()
                .orElseThrow(
                        () -> new EntityNotFoundException("Answer option not found for question: "
                                + userResponse.getQuestionId())
                );


        UserAnswer answer = new UserAnswer(session, question, answerOption);
        userAnswerService.createUserAnswer(answer);

        session.setCurrentTheta(updateTheta(session.getCurrentTheta(), answerOption.isCorrect(), question));
        testSessionService.saveSession(session);

        return new UserAnswerResponseDto(userResponse.getResponse());
    }

    @Override
    public FinalScoreResponseDto calculateFinalScore(Long userId, UUID sessionId) {
        TestSession session = testSessionService.getSessionByIdAndUserId(sessionId, userId);
        BigDecimal finalTheta = session.getCurrentTheta();
        BigDecimal finalScore = BigDecimal.valueOf(50)
                .add(finalTheta.multiply(BigDecimal.TEN))
                .setScale(2, RoundingMode.HALF_UP);
        session.setScore(finalScore);
        session = testSessionService.saveSession(session);
        return SessionMapper.toFinalScoreResponseDto(session);
    }

    private BigDecimal updateTheta(BigDecimal theta, boolean isCorrect, Question question) {
        BigDecimal a = question.getDiscrimination();
        BigDecimal b = question.getDifficulty();

        BigDecimal exponent = a.multiply(theta.subtract(b)).negate(); // -a * (theta - b)
        BigDecimal probability = BigDecimal.ONE.divide(
                BigDecimal.ONE.add(BigDecimal.valueOf(Math.exp(exponent.doubleValue()))),
                MathContext.DECIMAL128
        );

        BigDecimal correctness = isCorrect ? BigDecimal.ONE : BigDecimal.ZERO;
        BigDecimal step = a.multiply(correctness.subtract(probability));

        return theta.add(step).setScale(6, RoundingMode.HALF_UP);
    }

}
