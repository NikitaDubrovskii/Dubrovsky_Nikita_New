package dev.dubrovsky.dto.request.analytics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewAnalyticsRequest(

        @NotBlank(message = "Activity не может отсутствовать")
        String activity,

        @NotNull(message = "UserId не может отсутствовать")
        @Positive(message = "UserId должен быть больше 0")
        Integer userId

) {
}
