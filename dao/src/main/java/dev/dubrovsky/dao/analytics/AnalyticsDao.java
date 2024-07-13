package dev.dubrovsky.dao.analytics;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.analytics.Analytics;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AnalyticsDao implements IAnalyticsDao {

    private final EntityManagerFactory entityManagerFactory = ConnectionDataBase.getEntityManagerFactory();

    @Override
    public void create(Analytics entity) {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();

            String jpql = "INSERT INTO analytics (activity, user_id) VALUES (?, ?)";
            Query query = em.createNativeQuery(jpql);
            query.setParameter(1, entity.getActivity());
            query.setParameter(2, entity.getUserId());

            query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Analytics> getAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            String jpql = "SELECT a FROM Analytics a";
            TypedQuery<Analytics> query = em.createQuery(jpql, Analytics.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Analytics entity) {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();

            String jpql = "UPDATE analytics SET activity = ?, user_id = ? WHERE id = ?";
            Query query = em.createNativeQuery(jpql);
            query.setParameter(1, entity.getActivity());
            query.setParameter(2, entity.getUserId());
            query.setParameter(3, entity.getId());

            int i = query.executeUpdate();

            if (i == 0) {
                throw new DbException("Id " + entity.getId() + " не существует");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();

            String jpql = "DELETE FROM Analytics a WHERE a.id = :id";
            Query query = em.createQuery(jpql);
            query.setParameter("id", id);

            int i = query.executeUpdate();

            if (i == 0) {
                throw new DbException("Id " + id + " не существует");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

}
