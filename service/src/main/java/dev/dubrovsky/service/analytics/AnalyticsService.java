package dev.dubrovsky.service.analytics;

import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.repository.analytics.AnalyticsRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnalyticsService implements IAnalyticsService {

    private final AnalyticsRepository analyticsRepository;
    private final UserRepository userRepository;

    @Override
    public Analytics create(Analytics analytics) {
        validateAnalytics(analytics);
        ValidationUtil.checkEntityPresent(analytics.getUser().getId(), userRepository);

        return analyticsRepository.save(analytics);
    }

    @Override
    public Analytics getById(Integer id) {
        ValidationUtil.checkId(id, analyticsRepository);

        return analyticsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Analytics> getAll() {
        if (analyticsRepository.findAll().isEmpty()) {
            return null;
        } else {
            return analyticsRepository.findAll();
        }
    }

    @Override
    public Analytics update(Analytics analytics, Integer id) {
        validateAnalytics(analytics);
        ValidationUtil.checkId(id, analyticsRepository);
        ValidationUtil.checkEntityPresent(analytics.getUser().getId(), userRepository);

        analytics.setId(id);

        return analyticsRepository.save(analytics);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, analyticsRepository);

        analyticsRepository.deleteById(id);
        return "Удалено!";
    }

    private void validateAnalytics(Analytics analytics) {
        if (analytics == null) {
            throw new IllegalArgumentException("Аналитика не может отсутствовать");
        }
    }

}
