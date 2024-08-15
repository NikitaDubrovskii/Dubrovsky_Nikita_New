package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService implements ICartService {

    private final CartDao cartDao;
    private final UserDao userDao;
    private final CartItemDao cartItemDao;
    private final ProductDao productDao;

    @Override
    public Cart create(Cart cart) {
        validateCart(cart);
        ValidationUtil.checkEntityPresent(cart.getUser().getId(), userDao);

        return cartDao.create(cart);
    }

    @Override
    public Cart getById(Integer id) {
        ValidationUtil.checkId(id, cartDao);

        return cartDao.getById(id);
    }

    @Override
    public List<Cart> getAll() {
        if (cartDao.getAll().isEmpty() && cartDao.getAll() == null) {
            return null;
        } else {
            return cartDao.getAll();
        }
    }

    @Override
    public Cart update(Cart cart, Integer id) {
        validateCart(cart);
        ValidationUtil.checkEntityPresent(cart.getUser().getId(), userDao);
        ValidationUtil.checkId(id, cartDao);

        cart.setId(id);
        return cartDao.update(cart);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, cartDao);

        return cartDao.delete(id);
    }

    @Override
    public void getTotalPrice(Integer id) {
        float totalPrice = 0;
        List<CartItem> allByCartId = cartItemDao.getAllByCartId(id);
        if (allByCartId.isEmpty()) {
            throw new IllegalArgumentException("Корзины не существует с id: " + id);
        }
        for (CartItem cartItem : allByCartId) {
            Integer productId = cartItem.getProduct().getId();
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
