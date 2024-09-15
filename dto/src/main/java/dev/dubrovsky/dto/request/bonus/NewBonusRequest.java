package dev.dubrovsky.dto.request.bonus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewBonusRequest(

        @NotBlank(message = "Name не может отсутствовать")
        String name,

        String description,

        @NotNull(message = "Points не могут отсутствовать")
        @Positive(message = "Points должны быть больше 0")
        Integer points,

        @NotNull(message = "ProgramId не может отсутствовать")
        @Positive(message = "ProgramId должен быть больше 0")
        Integer programId

) {
}
