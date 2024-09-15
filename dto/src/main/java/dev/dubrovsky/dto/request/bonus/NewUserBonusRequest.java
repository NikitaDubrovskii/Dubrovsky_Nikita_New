package dev.dubrovsky.dto.request.bonus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewUserBonusRequest(

        @NotNull(message = "UserId не может отсутствовать")
        @Positive(message = "UserId должен быть больше 0")
        Integer userId,

        @NotNull(message = "UserId не может отсутствовать")
        @Positive(message = "UserId должен быть больше 0")
        Integer bonusId

) {
}
