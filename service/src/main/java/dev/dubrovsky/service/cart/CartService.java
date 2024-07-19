package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.util.validation.ValidationUtil;

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
        ValidationUtil.checkEntityPresent(cart.getUserId(), userDao);

        cartDao.create(cart);
    }

    @Override
    public void getById(Integer id) {
        ValidationUtil.checkId(id, cartDao);

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
        ValidationUtil.checkEntityPresent(cart.getUserId(), userDao);
        ValidationUtil.checkId(id, cartDao);

        cart.setId(id);
        cartDao.update(cart);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, cartDao);

        cartDao.delete(id);
    }

    private void validateCart(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Корзина не может отсутствовать");
        }
    }

}
