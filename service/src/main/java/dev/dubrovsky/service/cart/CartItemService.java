package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.dto.response.cart.CartItemResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.repository.cart.CartItemRepository;
import dev.dubrovsky.repository.cart.CartRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public void create(NewCartItemRequest request) {
        ValidationUtil.checkEntityPresent(request.getCartId(), cartRepository);
        ValidationUtil.checkEntityPresent(request.getProductId(), productRepository);

        CartItem cartItem = mapper.map(request, CartItem.class);
        cartItem.setCart(cartRepository
                .findById(request.getCartId())
                .orElseThrow(DbResponseErrorException::new));
        cartItem.setProduct(productRepository
                .findById(request.getProductId())
                .orElseThrow(DbResponseErrorException::new));

        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItemResponse getById(Integer id) {
        ValidationUtil.checkId(id, cartItemRepository);

        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return cartItem.mapToResponse();
    }

    @Override
    public List<CartItemResponse> getAll() {
        if (cartItemRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
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

        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.getQuantity() != null && request.getQuantity() != 0) {
            cartItem.setQuantity(request.getQuantity());
        }
        if (request.getProductId() != null && request.getProductId() != 0) {
            ValidationUtil.checkEntityPresent(request.getProductId(), productRepository);
            cartItem.setProduct(productRepository.findById(request.getProductId()).orElseThrow(DbResponseErrorException::new));
        }
        if (request.getCartId() != null && request.getCartId() != 0) {
            ValidationUtil.checkEntityPresent(request.getCartId(), cartRepository);
            cartItem.setCart(cartRepository.findById(request.getCartId()).orElseThrow(DbResponseErrorException::new));
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
