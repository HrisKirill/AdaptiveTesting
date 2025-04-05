package com.khrystoforov.adaptivetesting.refreshToken.service;

import com.khrystoforov.adaptivetesting.refreshToken.model.RefreshToken;
import com.khrystoforov.adaptivetesting.user.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(RefreshToken refreshToken);

    RefreshToken findByIdAndExpiresAtAfter(UUID refreshToken, LocalDateTime now);

    void revokeUserRefreshToken(User user);
}
