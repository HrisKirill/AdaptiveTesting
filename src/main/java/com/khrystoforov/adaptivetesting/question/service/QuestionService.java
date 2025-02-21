package com.khrystoforov.adaptivetesting.question.service;

import com.khrystoforov.adaptivetesting.question.model.Question;

import java.math.BigDecimal;

public interface QuestionService {
    Question create(Question question);

    Question getWithMinDifficultyDifference(BigDecimal theta);

    Question getQuestionByIdAndTopicId(Long questionId, Long topicId);
}
