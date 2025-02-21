package com.khrystoforov.adaptivetesting.answerOption.service.impl;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import com.khrystoforov.adaptivetesting.answerOption.repository.AnswerOptionRepository;
import com.khrystoforov.adaptivetesting.answerOption.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnswerOptionServiceImpl implements AnswerOptionService {
    private final AnswerOptionRepository repository;


    @Override
    public void saveAll(Set<AnswerOption> answerOptions) {
        repository.saveAll(answerOptions);
    }
}
