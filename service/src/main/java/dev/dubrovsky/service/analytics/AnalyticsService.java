package dev.dubrovsky.service.analytics;

import dev.dubrovsky.dao.analytics.AnalyticsDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.util.validation.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService implements IAnalyticsService {

    private final AnalyticsDao analyticsDao;
    private final UserDao userDao;

    public AnalyticsService(AnalyticsDao analyticsDao, UserDao userDao) {
        this.analyticsDao = analyticsDao;
        this.userDao = userDao;
    }

    @Override
    public Analytics create(Analytics analytics) {
        validateAnalytics(analytics);
        ValidationUtil.checkEntityPresent(analytics.getUser().getId(), userDao);

        return analyticsDao.create(analytics);
    }

    @Override
    public Analytics getById(Integer id) {
        ValidationUtil.checkId(id, analyticsDao);

        return analyticsDao.getById(id);
    }

    @Override
    public List<Analytics> getAll() {
        if (analyticsDao.getAll().isEmpty() && analyticsDao.getAll() == null) {
            return null;
        } else {
            return analyticsDao.getAll();
        }
    }

    @Override
    public Analytics update(Analytics analytics, Integer id) {
        validateAnalytics(analytics);
        ValidationUtil.checkId(id, analyticsDao);
        ValidationUtil.checkEntityPresent(analytics.getUser().getId(), userDao);

        analytics.setId(id);
        return analyticsDao.update(analytics);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, analyticsDao);

        return analyticsDao.delete(id);
    }

    private void validateAnalytics(Analytics analytics) {
        if (analytics == null) {
            throw new IllegalArgumentException("Аналитика не может отсутствовать");
        }
    }

}
