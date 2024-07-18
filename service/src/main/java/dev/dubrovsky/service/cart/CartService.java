package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.cart.Cart;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CartService implements ICartService {

    private final CartDao cartDao;
    private final UserDao userDao;

    public CartService(CartDao cartDao, UserDao userDao) {
        this.cartDao = cartDao;
        this.userDao = userDao;
    }

    @Override
    public void create(Cart cart) {
        validateCart(cart);
        checkUserPresent(cart.getUserId());

        cartDao.create(cart);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

        System.out.println(cartDao.getById(id));
    }

    @Override
    public void getAll() {
        if (cartDao.getAll().isEmpty() && cartDao.getAll() == null) {
            System.out.println("Таблица корзин пустая");
        } else {
            cartDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(Cart cart, Integer id) {
        validateCart(cart);
        checkUserPresent(cart.getUserId());
        checkId(id);

        cart.setId(id);
        cartDao.update(cart);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        cartDao.delete(id);
    }

    private void validateCart(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Корзина не может отсутствовать");
        }
    }

    private void checkUserPresent(Integer userId) {
        if (userId > 0) {
            Optional
                    .ofNullable(userDao.getById(userId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + userId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(cartDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
