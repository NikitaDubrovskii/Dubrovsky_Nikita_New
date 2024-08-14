package dev.dubrovsky.dao.cart;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.cart.Cart;

public class CartDao extends AbstractDao<Cart> implements ICartDao {

    public CartDao(Class<Cart> entityClass) {
        super(entityClass);
    }

}
