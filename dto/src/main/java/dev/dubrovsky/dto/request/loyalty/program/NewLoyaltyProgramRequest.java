package dev.dubrovsky.dto.request.loyalty.program;

import jakarta.validation.constraints.NotBlank;

public record NewLoyaltyProgramRequest(

        @NotBlank(message = "Name не может отсутствовать")
        String name,

        String description

) {
}
