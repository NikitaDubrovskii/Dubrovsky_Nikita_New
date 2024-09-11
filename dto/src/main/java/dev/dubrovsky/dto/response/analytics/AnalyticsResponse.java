package dev.dubrovsky.dto.response.analytics;

import dev.dubrovsky.dto.response.user.UserResponse;

import java.time.LocalDateTime;

public record AnalyticsResponse(

        Integer id,

        String activity,

        LocalDateTime timestamp,

        UserResponse user

) {
}
