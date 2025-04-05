package com.khrystoforov.adaptivetesting.auth.service;


import com.khrystoforov.adaptivetesting.auth.dto.request.LoginRequestDto;
import com.khrystoforov.adaptivetesting.auth.dto.request.RegisterRequestDto;
import com.khrystoforov.adaptivetesting.auth.dto.response.AuthenticationResponseDto;
import com.khrystoforov.adaptivetesting.user.model.User;

import java.util.UUID;

public interface AuthService {
    void register(RegisterRequestDto registerRequest);

    AuthenticationResponseDto login(LoginRequestDto loginRequest);

    AuthenticationResponseDto refreshToken(UUID tokenId);

    void revokeUserRefreshToken(User user);
}
