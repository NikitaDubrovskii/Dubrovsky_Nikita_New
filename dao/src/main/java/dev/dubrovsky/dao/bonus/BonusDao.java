package dev.dubrovsky.dao.bonus;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.exception.DbException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BonusDao implements IBonusDao {

    private final EntityManagerFactory entityManagerFactory = ConnectionDataBase.getEntityManagerFactory();

    @Override
    public void create(Bonus entity) {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();

            String jpql = "INSERT INTO bonuses (name, description, points, program_id) VALUES (?, ?, ?, ?)";
            Query query = em.createNativeQuery(jpql);
            query.setParameter(1, entity.getName());
            query.setParameter(2, entity.getDescription());
            query.setParameter(3, entity.getPoints());
            query.setParameter(4, entity.getProgramId());

            query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Bonus> getAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            String jpql = "SELECT b FROM Bonus b";
            TypedQuery<Bonus> query = em.createQuery(jpql, Bonus.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Bonus entity) {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();

            String jpql = "UPDATE bonuses SET name = ?, description = ?, points = ?, program_id = ? WHERE id = ?";
            Query query = em.createNativeQuery(jpql);
            query.setParameter(1, entity.getName());
            query.setParameter(2, entity.getDescription());
            query.setParameter(3, entity.getPoints());
            query.setParameter(4, entity.getProgramId());
            query.setParameter(5, entity.getId());

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

            String jpql = "DELETE FROM Bonus b WHERE b.id = :id";
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
