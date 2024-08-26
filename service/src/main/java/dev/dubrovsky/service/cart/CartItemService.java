package dev.dubrovsky.service.cart;

import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.repository.cart.CartItemRepository;
import dev.dubrovsky.repository.cart.CartRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartItem create(CartItem cartItem) {
        validateCartItem(cartItem);
        ValidationUtil.checkEntityPresent(cartItem.getCart().getId(), cartRepository);
        ValidationUtil.checkEntityPresent(cartItem.getProduct().getId(), productRepository);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getById(Integer id) {
        ValidationUtil.checkId(id, cartItemRepository);

        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<CartItem> getAll() {
        if (cartItemRepository.findAll().isEmpty()) {
            return null;
        } else {
            return cartItemRepository.findAll();
        }
    }

    @Override
    public CartItem update(CartItem cartItem, Integer id) {
        validateCartItem(cartItem);
        ValidationUtil.checkEntityPresent(cartItem.getCart().getId(), cartRepository);
        ValidationUtil.checkEntityPresent(cartItem.getProduct().getId(), productRepository);
        ValidationUtil.checkId(id, cartItemRepository);

        cartItem.setId(id);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, cartItemRepository);
        cartItemRepository.deleteById(id);

        return "Удалено!";
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
