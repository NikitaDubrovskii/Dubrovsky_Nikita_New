package dev.dubrovsky.dto.response.cart;

import dev.dubrovsky.dto.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ с корзиной")
public record CartResponse(

        @Schema(description = "Идентификатор корзины", example = "1")
        Integer id,

        @Schema(description = "Время создания", example = "2024-08-10T16:43:53.145856")
        LocalDateTime createdAt,

        @Schema(description = "Информация о пользователе")
        UserResponse user

) {
}
