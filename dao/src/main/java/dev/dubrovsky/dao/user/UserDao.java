package dev.dubrovsky.dao.user;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDao extends AbstractDao<User> implements IUserDao {

    EntityManagerFactory entityManagerFactory = ConnectionDataBase.getEntityManagerFactory();

    public UserDao(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    public User findByUsername(String username) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
          return null;
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public User findByUsernameOrEmail(String usernameOrEmail) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE username = :usernameOrEmail OR email = :usernameOrEmail", User.class);
            query.setParameter("usernameOrEmail", usernameOrEmail);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }
}
