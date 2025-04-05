package com.khrystoforov.adaptivetesting.adaptiveTest.controller;

import com.khrystoforov.adaptivetesting.adaptiveTest.service.AdaptiveTestService;
import com.khrystoforov.adaptivetesting.question.dto.response.QuestionResponseDto;
import com.khrystoforov.adaptivetesting.session.dto.response.FinalScoreResponseDto;
import com.khrystoforov.adaptivetesting.session.dto.response.SessionResponseDto;
import com.khrystoforov.adaptivetesting.user.model.User;
import com.khrystoforov.adaptivetesting.user.service.UserService;
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
    private final UserService userService;

    @PostMapping("/start/{topicName}")
    public SessionResponseDto startTest(@PathVariable String topicName) {
        User user = userService.getCurrentUser();
        return adaptiveTestService.startTest(user.getId(), topicName);
    }

    @GetMapping("/session/{sessionId}/next-question")
    public QuestionResponseDto getNextQuestion(@PathVariable UUID sessionId) {
        User user = userService.getCurrentUser();
        return adaptiveTestService.selectNextQuestion(user.getId(), sessionId);
    }

    @PostMapping("/session/{sessionId}/answer")
    public UserAnswerResponseDto submitAnswer(
            @PathVariable UUID sessionId,
            @Valid @RequestBody UserAnswerRequestDto userResponse) {
        User user = userService.getCurrentUser();
        return adaptiveTestService.submitAnswer(user.getId(), sessionId, userResponse);
    }

    @GetMapping("/result/{sessionId}")
    public FinalScoreResponseDto getFinalScore(@PathVariable UUID sessionId) {
        User user = userService.getCurrentUser();
        return adaptiveTestService.calculateFinalScore(user.getId(), sessionId);
    }

}
