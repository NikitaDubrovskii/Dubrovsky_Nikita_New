package dev.dubrovsky.dto.response.analytics;

import dev.dubrovsky.dto.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ с аналитикой")
public record AnalyticsResponse(

        @Schema(description = "Идентификатор аналитики", example = "1")
        Integer id,

        @Schema(description = "Активность", example = "Подписка")
        String activity,

        @Schema(description = "Время создания", example = "2024-08-10T16:43:53.145856")
        LocalDateTime timestamp,

        @Schema(description = "Информация о пользователе")
        UserResponse user

) {
}
