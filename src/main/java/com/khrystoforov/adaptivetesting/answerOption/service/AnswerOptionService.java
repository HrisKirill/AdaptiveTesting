package com.khrystoforov.adaptivetesting.answerOption.service;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;

import java.util.Set;

public interface AnswerOptionService {
    void saveAll(Set<AnswerOption> answerOptions);
}
