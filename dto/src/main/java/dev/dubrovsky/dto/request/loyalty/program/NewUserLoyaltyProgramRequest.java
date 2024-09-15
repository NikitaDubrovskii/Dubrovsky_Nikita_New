package dev.dubrovsky.dto.request.loyalty.program;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewUserLoyaltyProgramRequest(

        @NotNull(message = "UserId не может отсутствовать")
        @Positive(message = "UserId должен быть больше 0")
        Integer userId,

        @NotNull(message = "ProgramId не может отсутствовать")
        @Positive(message = "ProgramId должен быть больше 0")
        Integer programId

) {
}
