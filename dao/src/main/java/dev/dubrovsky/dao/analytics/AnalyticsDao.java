package dev.dubrovsky.dao.analytics;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.analytics.Analytics;

public class AnalyticsDao extends AbstractDao<Analytics> implements IAnalyticsDao {

    public AnalyticsDao(Class<Analytics> entityClass) {
        super(entityClass);
    }

    /*@Override
    public List<Analytics> findByUserId(Integer userId) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            TypedQuery<Analytics> query = em.createQuery("SELECT a FROM Analytics a WHERE a.userId = :userId", Analytics.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }*/

}
