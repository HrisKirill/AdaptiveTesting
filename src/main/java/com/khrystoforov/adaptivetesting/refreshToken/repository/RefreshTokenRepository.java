package com.khrystoforov.adaptivetesting.refreshToken.repository;


import com.khrystoforov.adaptivetesting.refreshToken.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByIdAndExpiresAtAfter(UUID id, LocalDateTime date);

    void deleteByUserId(Long userId);
}
