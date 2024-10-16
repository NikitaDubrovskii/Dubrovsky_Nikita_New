package dev.dubrovsky.dto.request.bonus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание нового бонуса")
public class NewBonusRequest {

    @NotBlank(message = "Name не может отсутствовать")
    @Schema(description = "Название бонуса", example = "Подписка")
    String name;

    @Schema(description = "Описание бонуса", example = "Бонус за подписку на ютуб")
    String description;

    @NotNull(message = "Points не могут отсутствовать")
    @Positive(message = "Points должны быть больше 0")
    @Schema(description = "Количество очков", example = "55")
    Integer points;

    @NotNull(message = "ProgramId не может отсутствовать")
    @Positive(message = "ProgramId должен быть больше 0")
    @Schema(description = "Id программы лояльности, для которой начислился бонус", example = "2")
    Integer programId;

}
