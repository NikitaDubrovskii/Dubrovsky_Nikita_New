package dev.dubrovsky.dao.analytics;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.analytics.Analytics;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsDao extends AbstractDao<Analytics> implements IAnalyticsDao {

    private EntityManagerFactory entityManagerFactory;

    public AnalyticsDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Analytics.class);
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
