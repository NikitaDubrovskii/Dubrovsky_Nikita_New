package dev.dubrovsky.dao.cart;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.cart.Cart;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class CartDao extends AbstractDao<Cart> implements ICartDao {

    public CartDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Cart.class);
    }

}
