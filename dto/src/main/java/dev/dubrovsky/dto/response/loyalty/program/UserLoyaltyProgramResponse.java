package dev.dubrovsky.dto.response.loyalty.program;

import dev.dubrovsky.dto.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ с программой лояльности пользователя")
public record UserLoyaltyProgramResponse(

        @Schema(description = "Информация о пользователе")
        UserResponse user,

        @Schema(description = "Информация о программе лояльности")
        LoyaltyProgramResponse loyaltyProgramResponse,

        @Schema(description = "Время выдачи", example = "2024-08-10T16:43:53.145856")
        LocalDateTime receivedAt

) {
}
