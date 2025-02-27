package com.khrystoforov.adaptivetesting.adaptiveTest.service;

import com.khrystoforov.adaptivetesting.question.dto.response.QuestionResponseDto;
import com.khrystoforov.adaptivetesting.session.dto.response.FinalScoreResponseDto;
import com.khrystoforov.adaptivetesting.session.dto.response.SessionResponseDto;
import com.khrystoforov.adaptivetesting.userAnswer.dto.request.UserAnswerRequestDto;
import com.khrystoforov.adaptivetesting.userAnswer.dto.response.UserAnswerResponseDto;

import java.util.UUID;

public interface AdaptiveTestService {

    SessionResponseDto startTest(Long userId, String topicName);

    QuestionResponseDto selectNextQuestion(Long userId, UUID sessionId);

    UserAnswerResponseDto submitAnswer(Long userId, UUID sessionId, UserAnswerRequestDto userResponse);

    FinalScoreResponseDto calculateFinalScore(Long userId, UUID sessionId);
}
