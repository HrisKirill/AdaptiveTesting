package com.khrystoforov.adaptivetesting.question.service.impl;

import com.khrystoforov.adaptivetesting.question.model.Question;
import com.khrystoforov.adaptivetesting.question.repository.QuestionRepository;
import com.khrystoforov.adaptivetesting.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;


    @Override
    public Question create(Question question) {
        return repository.save(question);
    }
}
