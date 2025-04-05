package com.khrystoforov.adaptivetesting.auth.service.impl;


import com.khrystoforov.adaptivetesting.auth.dto.request.LoginRequestDto;
import com.khrystoforov.adaptivetesting.auth.dto.request.RegisterRequestDto;
import com.khrystoforov.adaptivetesting.auth.dto.response.AuthenticationResponseDto;
import com.khrystoforov.adaptivetesting.auth.service.AuthService;
import com.khrystoforov.adaptivetesting.config.ApplicationProperties;
import com.khrystoforov.adaptivetesting.exception.EntityExistsException;
import com.khrystoforov.adaptivetesting.jwt.service.JWTTokenService;
import com.khrystoforov.adaptivetesting.refreshToken.model.RefreshToken;
import com.khrystoforov.adaptivetesting.refreshToken.service.RefreshTokenService;
import com.khrystoforov.adaptivetesting.user.model.Role;
import com.khrystoforov.adaptivetesting.user.model.User;
import com.khrystoforov.adaptivetesting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JWTTokenService jwtTokenservice;
    private final RefreshTokenService refreshTokenService;
    private final ApplicationProperties properties;

    @Override
    public void register(RegisterRequestDto registerRequest) {
        log.info("Register user with email {}", registerRequest.getEmail());
        if (userService.existsByEmail(registerRequest.getEmail())) {
            throw new EntityExistsException("Email is already exist!");
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .role(Role.USER)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userService.createUser(user);
    }

    @Override
    public AuthenticationResponseDto login(LoginRequestDto loginRequest) {
        log.info("Login user with email {}", loginRequest.getEmail());
        ApplicationProperties.RefreshTokenInfo refreshTokenInfo = properties.getRefreshToken();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwt = jwtTokenservice.generateToken(user);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiresAt(LocalDateTime.now().plus(refreshTokenInfo.expirationTime(), ChronoUnit.MILLIS));
        refreshToken = refreshTokenService.createRefreshToken(refreshToken);
        return new AuthenticationResponseDto(jwt, refreshToken.getId());
    }

    @Override
    public AuthenticationResponseDto refreshToken(UUID tokenId) {
        RefreshToken refreshToken = refreshTokenService.findByIdAndExpiresAtAfter(tokenId, LocalDateTime.now());
        String newAccessToken = jwtTokenservice.generateToken(refreshToken.getUser());
        return new AuthenticationResponseDto(newAccessToken, refreshToken.getId());
    }

    @Override
    public void revokeUserRefreshToken(User user) {
        refreshTokenService.revokeUserRefreshToken(user);
    }
}
