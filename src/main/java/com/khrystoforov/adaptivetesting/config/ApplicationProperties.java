package com.khrystoforov.adaptivetesting.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application")
@RequiredArgsConstructor
@Data
public class ApplicationProperties {
    private final JWTInfo jwt;
    private final RefreshTokenInfo refreshToken;

    public record JWTInfo(String secret, Long expirationTime) {
    }

    public record RefreshTokenInfo(Long expirationTime) {
    }
}
