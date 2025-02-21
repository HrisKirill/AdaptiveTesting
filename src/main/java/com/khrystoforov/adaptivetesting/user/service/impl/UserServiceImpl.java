package com.khrystoforov.adaptivetesting.user.service.impl;

import com.khrystoforov.adaptivetesting.exception.EntityNotFoundException;
import com.khrystoforov.adaptivetesting.user.User;
import com.khrystoforov.adaptivetesting.user.repository.UserRepository;
import com.khrystoforov.adaptivetesting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User getById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
    }
}
