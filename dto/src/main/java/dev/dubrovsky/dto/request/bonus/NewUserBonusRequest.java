package dev.dubrovsky.dto.request.bonus;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Запрос на выдачу бонуса для пользователя")
public class NewUserBonusRequest {

    @NotNull(message = "UserId не может отсутствовать")
    @Positive(message = "UserId должен быть больше 0")
    @Schema(description = "Id пользователя, кому выдан бонус", example = "1")
    Integer userId;

    @NotNull(message = "UserId не может отсутствовать")
    @Positive(message = "UserId должен быть больше 0")
    @Schema(description = "Id бонуса, который выдается пользователю", example = "2")
    Integer bonusId;

}
