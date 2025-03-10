package com.khrystoforov.adaptivetesting.session.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinalScoreResponseDto {
    private BigDecimal score;
}
