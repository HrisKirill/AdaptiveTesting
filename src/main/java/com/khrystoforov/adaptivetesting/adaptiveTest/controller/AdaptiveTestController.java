package com.khrystoforov.adaptivetesting.adaptiveTest.controller;

import com.khrystoforov.adaptivetesting.adaptiveTest.service.AdaptiveTestService;
import com.khrystoforov.adaptivetesting.question.dto.response.QuestionResponseDto;
import com.khrystoforov.adaptivetesting.session.dto.response.SessionResponseDto;
import com.khrystoforov.adaptivetesting.userAnswer.dto.request.UserAnswerRequestDto;
import com.khrystoforov.adaptivetesting.userAnswer.dto.response.UserAnswerResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class AdaptiveTestController {
    private final AdaptiveTestService adaptiveTestService;

    @PostMapping("/{userId}/start/{topicName}")
    public SessionResponseDto startTest(
            @PathVariable Long userId,
            @PathVariable String topicName
    ) {
        return adaptiveTestService.startTest(userId, topicName);
    }

    @GetMapping("/{userId}/session/{sessionId}/next-question")
    public QuestionResponseDto getNextQuestion(
            @PathVariable Long userId,
            @PathVariable UUID sessionId
    ) {
        return adaptiveTestService.selectNextQuestion(userId, sessionId);
    }

    @PostMapping("/{userId}/session/{sessionId}/answer")
    public UserAnswerResponseDto submitAnswer(
            @PathVariable Long userId,
            @PathVariable UUID sessionId,
            @Valid @RequestBody UserAnswerRequestDto userResponse) {
        return adaptiveTestService.submitAnswer(userId, sessionId, userResponse);
    }
}
