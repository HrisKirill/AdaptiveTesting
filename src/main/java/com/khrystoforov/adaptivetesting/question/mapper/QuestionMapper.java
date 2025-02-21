package com.khrystoforov.adaptivetesting.question.mapper;

import com.khrystoforov.adaptivetesting.answerOption.dto.response.AnswerOptionResponseDto;
import com.khrystoforov.adaptivetesting.answerOption.mapper.AnswerOptionMapper;
import com.khrystoforov.adaptivetesting.question.dto.response.QuestionResponseDto;
import com.khrystoforov.adaptivetesting.question.model.Question;

import java.util.Set;
import java.util.stream.Collectors;

public final class QuestionMapper {

    public static QuestionResponseDto toResponseDto(Question question) {
        Set<AnswerOptionResponseDto> options = question.getOptions().stream()
                .map(AnswerOptionMapper::toResponseDto)
                .collect(Collectors.toSet());
        return new QuestionResponseDto(question.getId(), question.getText(), options);
    }


}
