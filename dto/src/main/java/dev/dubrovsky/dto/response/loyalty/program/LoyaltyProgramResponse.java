package dev.dubrovsky.dto.response.loyalty.program;

import java.time.LocalDateTime;

public record LoyaltyProgramResponse(

        Integer id,

        String name,

        String description,

        LocalDateTime createdAt

) {
}
