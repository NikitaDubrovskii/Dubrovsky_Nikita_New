package dev.dubrovsky.dto.response.bonus;

import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с бонусом")
public record BonusResponse(

        @Schema(description = "Идентификатор бонуса", example = "1")
        Integer id,

        @Schema(description = "Название бонуса", example = "Подписка")
        String name,

        @Schema(description = "Описание бонуса", example = "Бонус за подписку на ютуб")
        String description,

        @Schema(description = "Количество очков", example = "55")
        Integer points,

        @Schema(description = "Информация о программе лояльности")
        LoyaltyProgramResponse program

) {
}
