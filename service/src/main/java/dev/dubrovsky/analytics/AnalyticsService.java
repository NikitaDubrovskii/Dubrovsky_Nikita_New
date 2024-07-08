package dev.dubrovsky.analytics;

public class AnalyticsService implements IAnalyticsService {

    private final IAnalyticsDao analyticsDao;

    public AnalyticsService(IAnalyticsDao analyticsDao) {
        this.analyticsDao = analyticsDao;
    }

    @Override
    public void create(Analytics analytics) {
        analyticsDao.create(analytics);
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
    public void update(Analytics analytics) {
        analyticsDao.update(analytics);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            analyticsDao.delete(id);
        }
    }

}
