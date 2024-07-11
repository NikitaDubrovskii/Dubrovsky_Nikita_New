package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.ICartItemDao;
import dev.dubrovsky.model.cart.CartItem;

public class CartItemService implements ICartItemService {

    private final ICartItemDao cartItemDao;

    public CartItemService(ICartItemDao cartItemDao) {
        this.cartItemDao = cartItemDao;
    }

    @Override
    public void create(CartItem entity) {
        cartItemDao.create(entity);
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
    public void update(CartItem entity) {
        cartItemDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            cartItemDao.delete(id);
        }
    }

}
