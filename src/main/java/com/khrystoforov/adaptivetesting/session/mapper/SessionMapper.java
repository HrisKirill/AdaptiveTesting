package com.khrystoforov.adaptivetesting.session.mapper;

import com.khrystoforov.adaptivetesting.session.dto.response.FinalScoreResponseDto;
import com.khrystoforov.adaptivetesting.session.model.TestSession;

public final class SessionMapper {

    public static FinalScoreResponseDto toFinalScoreResponseDto(TestSession session) {
        return new FinalScoreResponseDto(session.getScore());
    }
}
