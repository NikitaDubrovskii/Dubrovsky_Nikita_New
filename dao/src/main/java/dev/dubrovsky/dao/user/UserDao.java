package dev.dubrovsky.dao.user;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDao implements IUserDao {

    private final EntityManagerFactory entityManagerFactory = ConnectionDataBase.getEntityManagerFactory();

    @Override
    public void create(User user) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                em.persist(user);

                em.getTransaction().commit();
            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw ex;
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public User getById(Integer id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.find(User.class, id);
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                em.merge(user);

                em.getTransaction().commit();
            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw ex;
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                em.remove(em.find(User.class, id));

                em.getTransaction().commit();
            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw ex;
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public User findByUsername(String username) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
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
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

}
