package dev.dubrovsky.dao.cart;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.cart.CartItem;

public class CartItemDao extends AbstractDao<CartItem> implements ICartItemDao {

    public CartItemDao(Class<CartItem> entityClass) {
        super(entityClass);
    }

}
