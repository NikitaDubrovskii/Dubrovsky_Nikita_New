package dev.dubrovsky.dao.cart;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.cart.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemDao extends AbstractDao<CartItem> implements ICartItemDao {

    private final EntityManagerFactory entityManagerFactory;

    public CartItemDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, CartItem.class);
        this.entityManagerFactory = entityManagerFactory;
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
