package com.khrystoforov.adaptivetesting.session.service;

import com.khrystoforov.adaptivetesting.session.model.TestSession;

import java.util.UUID;

public interface TestSessionService {
    TestSession saveSession(TestSession testSession);

    TestSession getSessionByIdAndUserId(UUID sessionId, Long userId);

    Boolean isSessionExistsByTopicNameAndUserId(String topicName, Long userId);
}
