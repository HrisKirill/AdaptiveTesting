package com.khrystoforov.adaptivetesting.question.service.impl;

import com.khrystoforov.adaptivetesting.question.model.Question;
import com.khrystoforov.adaptivetesting.question.repository.QuestionRepository;
import com.khrystoforov.adaptivetesting.question.service.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question create(Question question) {
        return repository.save(question);
    }
}
