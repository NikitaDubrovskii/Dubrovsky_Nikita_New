package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.cart.CartItem;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        checkCartPresent(cartItem.getCartId());
        checkProductPresent(cartItem.getProductId());

        cartItemDao.create(cartItem);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

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
        checkCartPresent(cartItem.getCartId());
        checkProductPresent(cartItem.getProductId());
        checkId(id);

        cartItem.setId(id);
        cartItemDao.update(cartItem);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

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

    private void checkCartPresent(Integer cartId) {
        if (cartId > 0) {
            Optional
                    .ofNullable(cartDao.getById(cartId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + cartId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkProductPresent(Integer productId) {
        if (productId > 0) {
            Optional
                    .ofNullable(productDao.getById(productId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + productId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(cartItemDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
