package com.khrystoforov.adaptivetesting.answerOption.service.impl;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import com.khrystoforov.adaptivetesting.answerOption.repository.AnswerOptionRepository;
import com.khrystoforov.adaptivetesting.answerOption.service.AnswerOptionService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {
    private final AnswerOptionRepository repository;

    public AnswerOptionServiceImpl(AnswerOptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(Set<AnswerOption> answerOptions) {
        repository.saveAll(answerOptions);
    }
}
