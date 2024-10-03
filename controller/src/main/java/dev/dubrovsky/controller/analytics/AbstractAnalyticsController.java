package dev.dubrovsky.controller.analytics;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.analytics.UpdateAnalyticsRequest;
import dev.dubrovsky.dto.response.analytics.AnalyticsResponse;
import dev.dubrovsky.service.analytics.AnalyticsService;

public abstract class AbstractAnalyticsController extends AbstractController<AnalyticsService, AnalyticsResponse, NewAnalyticsRequest, UpdateAnalyticsRequest> {

    protected AbstractAnalyticsController(AnalyticsService service) {
        super(service);
    }

}
