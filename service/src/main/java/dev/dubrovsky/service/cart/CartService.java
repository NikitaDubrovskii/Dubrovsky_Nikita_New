package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.util.validation.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {

    private final CartDao cartDao;
    private final UserDao userDao;
    private final CartItemDao cartItemDao;
    private final ProductDao productDao;

    public CartService(CartDao cartDao, UserDao userDao, CartItemDao cartItemDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.userDao = userDao;
        this.cartItemDao = cartItemDao;
        this.productDao = productDao;
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

    @Override
    public void getTotalPrice(Integer id) {
        float totalPrice = 0;
        List<CartItem> allByCartId = cartItemDao.getAllByCartId(id);
        if (allByCartId.isEmpty()) {
            throw new IllegalArgumentException("Корзины не существует с id: " + id);
        }
        for (CartItem cartItem : allByCartId) {
            Integer productId = cartItem.getProductId();
            Product product = productDao.getById(productId);
            float i = product.getPrice() * cartItem.getQuantity();
            totalPrice += i;
        }

        System.out.println(totalPrice);
    }

    private void validateCart(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Корзина не может отсутствовать");
        }
    }

}
