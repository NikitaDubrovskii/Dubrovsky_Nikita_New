package dev.dubrovsky.dto.response.bonus;

import dev.dubrovsky.dto.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ с бонусом для пользователя")
public record UserBonusResponse(

        @Schema(description = "Информация о пользователе")
        UserResponse user,

        @Schema(description = "Информация о бонусе")
        BonusResponse bonus,

        @Schema(description = "Дата выдачи бонуса", example = "2024-08-10T16:43:53.145856")
        LocalDateTime receivedAt

) {
}
