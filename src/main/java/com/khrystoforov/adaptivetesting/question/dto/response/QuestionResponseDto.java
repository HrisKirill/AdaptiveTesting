package com.khrystoforov.adaptivetesting.question.dto.response;

import com.khrystoforov.adaptivetesting.answerOption.dto.response.AnswerOptionResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDto {
    private Long id;
    private String text;
    private Set<AnswerOptionResponseDto> options;
}
