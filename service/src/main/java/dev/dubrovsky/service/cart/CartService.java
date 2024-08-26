package dev.dubrovsky.service.cart;

import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.repository.cart.CartItemRepository;
import dev.dubrovsky.repository.cart.CartRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart create(Cart cart) {
        validateCart(cart);
        ValidationUtil.checkEntityPresent(cart.getUser().getId(), userRepository);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getById(Integer id) {
        ValidationUtil.checkId(id, cartRepository);

        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cart> getAll() {
        if (cartRepository.findAll().isEmpty()) {
            return null;
        } else {
            return cartRepository.findAll();
        }
    }

    @Override
    public Cart update(Cart cart, Integer id) {
        validateCart(cart);
        ValidationUtil.checkEntityPresent(cart.getUser().getId(), userRepository);
        ValidationUtil.checkId(id, cartRepository);

        cart.setId(id);
        return cartRepository.save(cart);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, cartRepository);

        cartRepository.deleteById(id);
        return "Удалено";
    }

    @Override
    public void getTotalPrice(Integer id) {
        float totalPrice = 0;
        List<CartItem> allByCartId = cartItemRepository.findAllByCartId(id);
        if (allByCartId.isEmpty()) {
            throw new IllegalArgumentException("Корзины не существует с id: " + id);
        }
        for (CartItem cartItem : allByCartId) {
            Integer productId = cartItem.getProduct().getId();
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                float i = product.getPrice() * cartItem.getQuantity();
                totalPrice += i;
            }
        }

        System.out.println(totalPrice);
    }

    private void validateCart(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Корзина не может отсутствовать");
        }
    }

}
