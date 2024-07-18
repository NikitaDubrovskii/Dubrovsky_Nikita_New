package dev.dubrovsky.service.analytics;

import dev.dubrovsky.dao.analytics.AnalyticsDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.analytics.Analytics;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        checkUserPresent(analytics.getUserId());

        analyticsDao.create(analytics);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

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
        checkUserPresent(analytics.getUserId());
        checkId(id);

        analytics.setId(id);
        analyticsDao.update(analytics);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        analyticsDao.delete(id);
    }

    private void validateAnalytics(Analytics analytics) {
        if (analytics == null) {
            throw new IllegalArgumentException("Аналитика не может отсутствовать");
        }
    }

    private void checkUserPresent(Integer userId) {
        if (userId > 0) {
            Optional
                    .ofNullable(userDao.getById(userId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + userId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(analyticsDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
