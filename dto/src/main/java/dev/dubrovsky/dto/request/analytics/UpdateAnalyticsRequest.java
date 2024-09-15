package dev.dubrovsky.dto.request.analytics;

import jakarta.validation.constraints.Positive;

public record UpdateAnalyticsRequest(

        String activity,

        @Positive(message = "UserId должен быть больше 0")
        Integer userId

) {
}
