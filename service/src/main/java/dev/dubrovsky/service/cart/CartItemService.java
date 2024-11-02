package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.dto.response.cart.CartItemResponse;
import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.cart.CartItemRepository;
import dev.dubrovsky.repository.cart.CartRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartService cartService;

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public void create(NewCartItemRequest request) {
        ValidationUtil.checkEntityPresent(request.getCartId(), cartRepository);
        ValidationUtil.checkEntityPresent(request.getProductId(), productRepository);

        CartItem cartItem = mapper
                .typeMap(NewCartItemRequest.class, CartItem.class)
                .addMappings(mapper -> mapper.skip(CartItem::setId))
                .map(request);
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

    @Override
    public void addItemToCart(String username, NewCartItemRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));

        Integer cartId;
        if (user.getCarts() == null || user.getCarts().isEmpty()) {
            cartService.create(new NewCartRequest(user.getId()));
            cartId = cartService.getByUserId(user.getId()).getId();
        } else {
            cartId = user.getCarts().stream().max(Comparator.comparing(Cart::getId)).get().getId();
        }

        request.setCartId(cartId);
        create(request);
    }

    @Override
    public List<CartItemResponse> getCartItemsByUser(String username, Integer cartId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));
        ValidationUtil.checkId(cartId, cartRepository);

        if (user.getCarts().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<CartItemResponse> responses = new ArrayList<>();
            List<CartItem> all = cartItemRepository.findAllByCartId(cartId);

            if (all.isEmpty()) {
                throw new EntityNotFoundException("По запросу ничего не найдено :(");
            }

            all.forEach(cartItem -> responses.add(cartItem.mapToResponse()));

            return responses;
        }
    }

    @Override
    public CartItemResponse getOneByUser(String username, Integer cartId, Integer itemId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));
        ValidationUtil.checkId(cartId, cartRepository);
        ValidationUtil.checkId(itemId, cartItemRepository);

        if (user.getCarts().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            if (cartItemRepository.findAllByCartId(cartId).isEmpty()) {
                throw new EntityNotFoundException("По запросу ничего не найдено :(");
            } else {
                List<CartItem> all = cartItemRepository.findAllByCartId(cartId);
                return all.stream()
                        .filter(cartItem -> cartItem.getId().equals(itemId))
                        .findFirst()
                        .orElseThrow(DbResponseErrorException::new)
                        .mapToResponse();
            }
        }
    }

    @Override
    public void updateCartItem(String username, UpdateCartItemRequest request, Integer itemId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));
        ValidationUtil.checkId(itemId, cartItemRepository);

        boolean hasItem = user.getCarts().stream()
                .anyMatch(cart -> cart.getItems().stream()
                        .anyMatch(cartItem -> cartItem.getId().equals(itemId)));

        if (hasItem) update(request, itemId);
        else throw new EntityNotFoundException("По запросу ничего не найдено :(");
    }

    @Override
    public void deleteItemFromCart(String username, Integer itemId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));
        ValidationUtil.checkId(itemId, cartItemRepository);

        boolean hasItem = user.getCarts().stream()
                .anyMatch(cart -> cart.getItems().stream()
                        .anyMatch(cartItem -> cartItem.getId().equals(itemId)));

        if (hasItem) delete(itemId);
        else throw new EntityNotFoundException("По запросу ничего не найдено :(");
    }

}
