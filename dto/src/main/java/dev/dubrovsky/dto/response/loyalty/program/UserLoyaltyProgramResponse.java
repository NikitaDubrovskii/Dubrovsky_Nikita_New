package dev.dubrovsky.dto.response.loyalty.program;

import dev.dubrovsky.dto.response.user.UserResponse;

import java.time.LocalDateTime;

public record UserLoyaltyProgramResponse(

        UserResponse user,

        LoyaltyProgramResponse loyaltyProgramResponse,

        LocalDateTime receivedAt

) {
}
