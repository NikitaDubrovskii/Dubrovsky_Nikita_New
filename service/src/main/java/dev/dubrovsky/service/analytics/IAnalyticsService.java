package dev.dubrovsky.service.analytics;

import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.analytics.UpdateAnalyticsRequest;
import dev.dubrovsky.dto.response.analytics.AnalyticsResponse;
import dev.dubrovsky.service.ICommonService;

public interface IAnalyticsService extends ICommonService<AnalyticsResponse, NewAnalyticsRequest, UpdateAnalyticsRequest> {


}
