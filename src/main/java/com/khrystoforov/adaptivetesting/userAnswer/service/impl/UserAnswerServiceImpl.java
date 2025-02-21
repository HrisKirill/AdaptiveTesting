package com.khrystoforov.adaptivetesting.userAnswer.service.impl;

import com.khrystoforov.adaptivetesting.userAnswer.model.UserAnswer;
import com.khrystoforov.adaptivetesting.userAnswer.repository.UserAnswerRepository;
import com.khrystoforov.adaptivetesting.userAnswer.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;

    @Override
    public UserAnswer createUserAnswer(UserAnswer answer) {
        return userAnswerRepository.save(answer);
    }
}
