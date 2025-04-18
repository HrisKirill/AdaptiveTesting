package com.khrystoforov.adaptivetesting.session.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseDto {
    private UUID id;
}
