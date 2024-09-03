package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemDao cartItemDao;
    private final CartDao cartDao;
    private final ProductDao productDao;

    @Override
    public CartItem create(CartItem cartItem) {
        validateCartItem(cartItem);
        ValidationUtil.checkEntityPresent(cartItem.getCart().getId(), cartDao);
        ValidationUtil.checkEntityPresent(cartItem.getProduct().getId(), productDao);

        return cartItemDao.create(cartItem);
    }

    @Override
    public CartItem getById(Integer id) {
        ValidationUtil.checkId(id, cartItemDao);

        return cartItemDao.getById(id);
    }

    @Override
    public List<CartItem> getAll() {
        if (cartItemDao.getAll().isEmpty() && cartItemDao.getAll() == null) {
            return null;
        } else {
            return cartItemDao.getAll();
        }
    }

    @Override
    public CartItem update(CartItem cartItem, Integer id) {
        validateCartItem(cartItem);
        ValidationUtil.checkEntityPresent(cartItem.getCart().getId(), cartDao);
        ValidationUtil.checkEntityPresent(cartItem.getProduct().getId(), productDao);
        ValidationUtil.checkId(id, cartItemDao);

        cartItem.setId(id);
        return cartItemDao.update(cartItem);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, cartItemDao);

        return cartItemDao.delete(id);
    }

    private void validateCartItem(CartItem cartItem) {
        if (cartItem == null) {
            throw new IllegalArgumentException("Предмет в корзине не может отсутствовать");
        }
        if (cartItem.getQuantity() == null || cartItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Количество не может отсутствовать");
        }
    }

}
