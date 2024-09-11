package dev.dubrovsky.service.analytics;

import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.analytics.UpdateAnalyticsRequest;
import dev.dubrovsky.dto.response.analytics.AnalyticsResponse;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.repository.analytics.AnalyticsRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnalyticsService implements IAnalyticsService {

    private final AnalyticsRepository analyticsRepository;
    private final UserRepository userRepository;

    @Override
    public void create(NewAnalyticsRequest request) {
        Analytics analytics = new Analytics();
        analytics.setActivity(request.activity());
        analytics.setUser(userRepository
                .findById(request.userId())
                .orElse(null));
        analytics.setTimestamp(LocalDateTime.now());

        validateAnalytics(analytics);
        ValidationUtil.checkEntityPresent(analytics.getUser().getId(), userRepository);

        analyticsRepository.save(analytics);
    }

    @Override
    public AnalyticsResponse getById(Integer id) {
        ValidationUtil.checkId(id, analyticsRepository);

        Analytics analytics = analyticsRepository.findById(id).orElse(null);
        return analytics.mapToResponse();
    }

    @Override
    public List<AnalyticsResponse> getAll() {
        if (analyticsRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<AnalyticsResponse> responses = new ArrayList<>();
            List<Analytics> all = analyticsRepository.findAll();

            all.forEach(analytics -> responses.add(analytics.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateAnalyticsRequest request, Integer id) {
        Analytics analytics = new Analytics();
        analytics.setActivity(request.activity());
        analytics.setUser(userRepository
                .findById(request.userId())
                .orElse(null));

        validateAnalytics(analytics);
        ValidationUtil.checkId(id, analyticsRepository);
        ValidationUtil.checkEntityPresent(analytics.getUser().getId(), userRepository);

        analytics.setId(id);

        analyticsRepository.save(analytics);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, analyticsRepository);
        analyticsRepository.deleteById(id);
    }

    private void validateAnalytics(Analytics analytics) {
        if (analytics == null) {
            throw new IllegalArgumentException("Аналитика не может отсутствовать");
        }
    }

}
