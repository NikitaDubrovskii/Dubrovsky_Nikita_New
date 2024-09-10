package dev.dubrovsky.dto.request.analytics;

public record NewAnalyticsRequest(

        String activity,

        Integer userId

) {
}
