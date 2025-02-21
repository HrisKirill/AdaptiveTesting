package com.khrystoforov.adaptivetesting.session.service.impl;

import com.khrystoforov.adaptivetesting.exception.EntityNotFoundException;
import com.khrystoforov.adaptivetesting.session.model.TestSession;
import com.khrystoforov.adaptivetesting.session.repository.TestSessionRepository;
import com.khrystoforov.adaptivetesting.session.service.TestSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestSessionServiceImpl implements TestSessionService {
    private final TestSessionRepository sessionRepository;


    @Override
    public TestSession saveSession(TestSession testSession) {
        return sessionRepository.save(testSession);
    }

    @Override
    public TestSession getSessionByIdAndUserId(UUID sessionId, Long userId) {
        return sessionRepository.findTestSessionByIdAndUserId(sessionId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Not found session with id %s and user id %d",
                                sessionId, userId)));
    }


    @Override
    public Boolean isSessionExistsByTopicNameAndUserId(String topicName, Long userId) {
        return sessionRepository.existsByTopicNameAndUserId(topicName, userId);
    }
}
