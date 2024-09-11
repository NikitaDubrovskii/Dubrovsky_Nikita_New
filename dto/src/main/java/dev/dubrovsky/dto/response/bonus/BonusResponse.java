package dev.dubrovsky.dto.response.bonus;

import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;

public record BonusResponse(

        Integer id,

        String name,

        String description,

        Integer points,

        LoyaltyProgramResponse program

) {
}
