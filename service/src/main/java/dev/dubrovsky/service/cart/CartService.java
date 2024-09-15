package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.exception.EntityNotFoundException;
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

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public void create(NewCartRequest request) {
        ValidationUtil.checkEntityPresent(request.userId(), userRepository);

        Cart cart = new Cart();
        cart.setUser(userRepository.
                findById(request.userId()).
                orElse(null));

        cartRepository.save(cart);
    }

    @Override
    public CartResponse getById(Integer id) {
        ValidationUtil.checkId(id, cartRepository);

        Cart cart = cartRepository.findById(id).orElse(null);
        return cart != null ? cart.mapToResponse() : null;
    }

    @Override
    public List<CartResponse> getAll() {
        if (cartRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<CartResponse> responses = new ArrayList<>();
            List<Cart> all = cartRepository.findAll();

            all.forEach(cart -> responses.add(cart.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateCartRequest request, Integer id) {
        ValidationUtil.checkId(id, cartRepository);

        Cart cart = cartRepository.findById(id).orElse(null);
        assert cart != null;

        if (request.userId() != null && request.userId() != 0) {
            ValidationUtil.checkEntityPresent(request.userId(), userRepository);
            cart.setUser(userRepository.findById(request.userId()).orElse(null));
        }
        cart.setId(id);

        cartRepository.save(cart);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, cartRepository);
        cartRepository.deleteById(id);
    }

    @Override
    public Float getTotalPrice(Integer id) {
        float totalPrice = 0;
        List<CartItem> allByCartId = cartItemRepository.findAllByCartId(id);
        if (allByCartId.isEmpty()) {
            throw new EntityNotFoundException("Корзины не существует с id: " + id);
        }
        for (CartItem cartItem : allByCartId) {
            Integer productId = cartItem.getProduct().getId();
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                float i = product.getPrice() * cartItem.getQuantity();
                totalPrice += i;
            }
        }

        return totalPrice;
    }

}
