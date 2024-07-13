package dev.dubrovsky.dao.user;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.model.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    private final EntityManagerFactory entityManagerFactory = ConnectionDataBase.getEntityManagerFactory();

    @Override
    public void create(User entity) {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();

            String jpql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            Query query = em.createNativeQuery(jpql);
            query.setParameter(1, entity.getUsername());
            query.setParameter(2, entity.getPassword());
            query.setParameter(3, entity.getEmail());

            query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            String jpql = "SELECT u FROM User u";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try (EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();

            String jpql = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";
            Query query = em.createNativeQuery(jpql);
            query.setParameter(1, entity.getUsername());
            query.setParameter(2, entity.getPassword());
            query.setParameter(3, entity.getEmail());
            query.setParameter(4, entity.getId());

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

            String jpql = "DELETE FROM User u WHERE u.id = :id";
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
