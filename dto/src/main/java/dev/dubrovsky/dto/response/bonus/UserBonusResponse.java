package dev.dubrovsky.dto.response.bonus;

import dev.dubrovsky.dto.response.user.UserResponse;

import java.time.LocalDateTime;

public record UserBonusResponse(

        UserResponse user,

        BonusResponse bonus,

        LocalDateTime receivedAt

) {
}
