package com.khrystoforov.adaptivetesting.session.repository;

import com.khrystoforov.adaptivetesting.session.model.TestSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TestSessionRepository extends JpaRepository<TestSession, UUID> {
    Optional<TestSession> findTestSessionByIdAndUserId(UUID sessionId, Long userId);

    Boolean existsByTopicNameAndUserId(String topicName, Long userId);
}
