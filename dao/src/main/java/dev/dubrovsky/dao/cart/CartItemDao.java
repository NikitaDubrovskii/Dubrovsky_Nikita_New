package dev.dubrovsky.dao.cart;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.cart.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CartItemDao extends AbstractDao<CartItem> implements ICartItemDao {

    EntityManagerFactory entityManagerFactory = ConnectionDataBase.getEntityManagerFactory();

    public CartItemDao(Class<CartItem> entityClass) {
        super(entityClass);
    }

    @Override
    public List<CartItem> getAllByCartId(int cartId) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            TypedQuery<CartItem> query = em.createQuery("SELECT c FROM CartItem c WHERE cartId = :cartId", CartItem.class);
            query.setParameter("cartId", cartId);
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }
}
