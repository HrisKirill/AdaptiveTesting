package com.khrystoforov.adaptivetesting.question.service.impl;

import com.khrystoforov.adaptivetesting.exception.EntityNotFoundException;
import com.khrystoforov.adaptivetesting.question.model.Question;
import com.khrystoforov.adaptivetesting.question.repository.QuestionRepository;
import com.khrystoforov.adaptivetesting.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;


    @Override
    public Question create(Question question) {
        return repository.save(question);
    }

    @Override
    public Question getWithMinDifficultyDifference(BigDecimal theta) {
        return repository.findClosestQuestion(theta)
                .orElseThrow(() -> new EntityNotFoundException("No available questions"));
    }

    @Override
    public Question getQuestionByIdAndTopicId(Long questionId, Long topicId) {
        return repository.findByIdAndTopicId(questionId, topicId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not found question with id %d for topic %d",
                        questionId, topicId)));

    }
}
