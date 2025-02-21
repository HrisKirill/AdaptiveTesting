package com.khrystoforov.adaptivetesting.userAnswer.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserAnswerRequestDto {
    @NotNull
    private Long questionId;
    @NotNull
    private String response;
}
