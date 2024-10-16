package dev.dubrovsky.dto.response.loyalty.program;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ с программой лояльности")
public record LoyaltyProgramResponse(

        @Schema(description = "Идентификатор программы лояльности", example = "1")
        Integer id,

        @Schema(description = "Название программы лояльности", example = "Золото 1")
        String name,

        @Schema(description = "Описание программы лояльности", example = "Уровень Золото 1")
        String description,

        @Schema(description = "Время создания", example = "2024-08-10T16:43:53.145856")
        LocalDateTime createdAt

) {
}
