package com.khrystoforov.adaptivetesting.user.service.impl;

import com.khrystoforov.adaptivetesting.exception.EntityNotFoundException;
import com.khrystoforov.adaptivetesting.user.model.User;
import com.khrystoforov.adaptivetesting.user.repository.UserRepository;
import com.khrystoforov.adaptivetesting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User createUser(User user) {
        log.info("Create user {}", user);
        return repository.save(user);
    }

    @Override
    public User getById(Long userId) {
        log.info("Get user with id {}", userId);
        return repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Get user by email {}", email);
        return repository.findByEmail(email)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(String.format("User not found with email %s", email)));
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("Exist user by email {}", email);
        return repository.existsUserByEmail(email);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getUserByEmail(auth.getName());
    }
}
