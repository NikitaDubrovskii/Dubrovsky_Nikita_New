package dev.dubrovsky.service.analytics;

import dev.dubrovsky.dao.analytics.AnalyticsDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.util.validation.ValidationUtil;

public class AnalyticsService implements IAnalyticsService {

    private final AnalyticsDao analyticsDao;
    private final UserDao userDao;

    public AnalyticsService(AnalyticsDao analyticsDao, UserDao userDao) {
        this.analyticsDao = analyticsDao;
        this.userDao = userDao;
    }

    @Override
    public void create(Analytics analytics) {
        validateAnalytics(analytics);
        ValidationUtil.checkEntityPresent(analytics.getUserId(), userDao);

        analyticsDao.create(analytics);
    }

    @Override
    public void getById(Integer id) {
        ValidationUtil.checkId(id, analyticsDao);

        System.out.println(analyticsDao.getById(id));
    }

    @Override
    public void getAll() {
        if (analyticsDao.getAll().isEmpty() && analyticsDao.getAll() == null) {
            System.out.println("Таблица аналитики пустая");
        } else {
            analyticsDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(Analytics analytics, Integer id) {
        validateAnalytics(analytics);
        ValidationUtil.checkId(id, analyticsDao);
        ValidationUtil.checkEntityPresent(analytics.getUserId(), userDao);

        analytics.setId(id);
        analyticsDao.update(analytics);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, analyticsDao);

        analyticsDao.delete(id);
    }

    private void validateAnalytics(Analytics analytics) {
        if (analytics == null) {
            throw new IllegalArgumentException("Аналитика не может отсутствовать");
        }
    }

}
