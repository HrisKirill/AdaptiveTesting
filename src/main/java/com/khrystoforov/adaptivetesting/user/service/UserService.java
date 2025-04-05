package com.khrystoforov.adaptivetesting.user.service;


import com.khrystoforov.adaptivetesting.user.model.User;

public interface UserService {
    User createUser(User user);

    User getById(Long userId);

    User getUserByEmail(String email);

    boolean existsByEmail(String email);

    User getCurrentUser();
}
