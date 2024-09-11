package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.dto.response.cart.CartItemResponse;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.repository.cart.CartItemRepository;
import dev.dubrovsky.repository.cart.CartRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public void create(NewCartItemRequest request) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(request.quantity());
        cartItem.setCart(cartRepository
                .findById(request.cartId())
                .orElse(null));
        cartItem.setProduct(productRepository
                .findById(request.productId())
                .orElse(null));

        validateCartItem(cartItem);
        ValidationUtil.checkEntityPresent(cartItem.getCart().getId(), cartRepository);
        ValidationUtil.checkEntityPresent(cartItem.getProduct().getId(), productRepository);

        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItemResponse getById(Integer id) {
        ValidationUtil.checkId(id, cartItemRepository);

        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        return cartItem.mapToResponse();
    }

    @Override
    public List<CartItemResponse> getAll() {
        if (cartItemRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<CartItemResponse> responses = new ArrayList<>();
            List<CartItem> all = cartItemRepository.findAll();

            all.forEach(cartItem -> responses.add(cartItem.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateCartItemRequest request, Integer id) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(request.quantity());
        cartItem.setCart(cartRepository
                .findById(request.cartId())
                .orElse(null));
        cartItem.setProduct(productRepository
                .findById(request.productId())
                .orElse(null));

        validateCartItem(cartItem);
        ValidationUtil.checkEntityPresent(cartItem.getCart().getId(), cartRepository);
        ValidationUtil.checkEntityPresent(cartItem.getProduct().getId(), productRepository);
        ValidationUtil.checkId(id, cartItemRepository);

        cartItem.setId(id);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, cartItemRepository);
        cartItemRepository.deleteById(id);
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
