package dev.dubrovsky.dto.request.bonus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на обновление бонуса")
public class UpdateBonusRequest{

    @Schema(description = "Название бонуса", example = "Подписка")
    String name;

    @Schema(description = "Описание бонуса", example = "Бонус за подписку на ютуб")
    String description;

    @Schema(description = "Количество очков", example = "55")
    Integer points;

    @Schema(description = "Id программы лояльности, для которой начислился бонус", example = "2")
    Integer programId;

}
