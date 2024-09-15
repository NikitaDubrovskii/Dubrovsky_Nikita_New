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
        ValidationUtil.checkEntityPresent(request.cartId(), cartRepository);
        ValidationUtil.checkEntityPresent(request.productId(), productRepository);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(request.quantity());
        cartItem.setCart(cartRepository
                .findById(request.cartId())
                .orElse(null));
        cartItem.setProduct(productRepository
                .findById(request.productId())
                .orElse(null));

        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItemResponse getById(Integer id) {
        ValidationUtil.checkId(id, cartItemRepository);

        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        return cartItem != null ? cartItem.mapToResponse() : null;
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
        ValidationUtil.checkId(id, cartItemRepository);

        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        assert cartItem != null;

        if (request.quantity() != null && request.quantity() != 0) {
            cartItem.setQuantity(request.quantity());
        }
        if (request.productId() != null && request.productId() != 0) {
            ValidationUtil.checkEntityPresent(request.productId(), productRepository);
            cartItem.setProduct(productRepository.findById(request.productId()).orElse(null));
        }
        if (request.cartId() != null && request.cartId() != 0) {
            ValidationUtil.checkEntityPresent(request.cartId(), cartRepository);
            cartItem.setCart(cartRepository.findById(request.cartId()).orElse(null));
        }
        cartItem.setId(id);

        cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, cartItemRepository);
        cartItemRepository.deleteById(id);
    }

}
