package com.khrystoforov.adaptivetesting.answerOption.mapper;

import com.khrystoforov.adaptivetesting.answerOption.dto.response.AnswerOptionResponseDto;
import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;

public final class AnswerOptionMapper {

    public static AnswerOptionResponseDto toResponseDto(AnswerOption answerOption) {
        return new AnswerOptionResponseDto(answerOption.getText());
    }
}
