package dev.dubrovsky.dao.cart;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.cart.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CartDao implements ICartDao {

    private final EntityManagerFactory entityManagerFactory = ConnectionDataBase.getEntityManagerFactory();

    @Override
    public void create(Cart cart) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                em.persist(cart);

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
    public Cart getById(Integer id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.find(Cart.class, id);
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Cart> getAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.createQuery("SELECT c FROM Cart c", Cart.class).getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Cart cart) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                em.merge(cart);

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

                em.remove(em.find(Cart.class, id));

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

}
