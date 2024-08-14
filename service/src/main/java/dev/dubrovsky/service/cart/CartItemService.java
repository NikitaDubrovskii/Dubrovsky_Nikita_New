package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.util.validation.ValidationUtil;

public class CartItemService implements ICartItemService {

    private final CartItemDao cartItemDao;
    private final CartDao cartDao;
    private final ProductDao productDao;

    public CartItemService(CartItemDao cartItemDao, CartDao cartDao, ProductDao productDao) {
        this.cartItemDao = cartItemDao;
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    @Override
    public void create(CartItem cartItem) {
        validateCartItem(cartItem);
        ValidationUtil.checkEntityPresent(cartItem.getCartId(), cartDao);
        ValidationUtil.checkEntityPresent(cartItem.getProductId(), productDao);

        cartItemDao.create(cartItem);
    }

    @Override
    public void getById(Integer id) {
        ValidationUtil.checkId(id, cartItemDao);

        System.out.println(cartItemDao.getById(id));
    }

    @Override
    public void getAll() {
        if (cartItemDao.getAll().isEmpty() && cartItemDao.getAll() == null) {
            System.out.println("Таблица вещей корзины пустая");
        } else {
            cartItemDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(CartItem cartItem, Integer id) {
        validateCartItem(cartItem);
        ValidationUtil.checkEntityPresent(cartItem.getCartId(), cartDao);
        ValidationUtil.checkEntityPresent(cartItem.getProductId(), productDao);
        ValidationUtil.checkId(id, cartItemDao);

        cartItem.setId(id);
        cartItemDao.update(cartItem);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, cartItemDao);

        cartItemDao.delete(id);
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
