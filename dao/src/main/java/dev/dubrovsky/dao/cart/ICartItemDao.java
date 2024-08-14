package dev.dubrovsky.dao.cart;

import dev.dubrovsky.model.cart.CartItem;

import java.util.List;

public interface ICartItemDao {

    List<CartItem> getAllByCartId(int cartId);

}
