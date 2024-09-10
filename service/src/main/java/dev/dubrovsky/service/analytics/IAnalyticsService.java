package dev.dubrovsky.service.analytics;

import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.analytics.UpdateAnalyticsRequest;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.analytics.Analytics;

public interface IAnalyticsService extends ICommonService<Analytics, NewAnalyticsRequest, UpdateAnalyticsRequest> {
}
