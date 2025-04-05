package com.khrystoforov.adaptivetesting.jwt.service;


import com.khrystoforov.adaptivetesting.user.model.User;

public interface JWTTokenService {
    String generateToken(User user);

    boolean validateToken(String token);

    User parseToken(String token);
}
