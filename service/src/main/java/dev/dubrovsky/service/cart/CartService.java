package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.product.Product;
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
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Override
    public void create(NewCartRequest request) {
        ValidationUtil.checkEntityPresent(request.getUserId(), userRepository);

        Cart cart = mapper
                .typeMap(NewCartRequest.class, Cart.class)
                .addMappings(mapper -> mapper.skip(Cart::setId))
                .map(request);
        cart.setUser(userRepository
                .findById(request.getUserId())
                .orElseThrow(DbResponseErrorException::new));

        cartRepository.save(cart);
    }

    @Override
    public CartResponse getById(Integer id) {
        ValidationUtil.checkId(id, cartRepository);

        Cart cart = cartRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return cart.mapToResponse();
    }

    @Override
    public List<CartResponse> getAll() {
        if (cartRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
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

        Cart cart = cartRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.getUserId() != null && request.getUserId() != 0) {
            ValidationUtil.checkEntityPresent(request.getUserId(), userRepository);
            cart.setUser(userRepository.findById(request.getUserId()).orElseThrow(DbResponseErrorException::new));
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
            Product product = productRepository.findById(productId).orElseThrow(DbResponseErrorException::new);
            if (product != null) {
                float i = product.getPrice() * cartItem.getQuantity();
                totalPrice += i;
            }
        }

        return totalPrice;
    }

    @Override
    public List<CartResponse> getCartsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));

        if (user.getCarts().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<CartResponse> responses = new ArrayList<>();
            List<Cart> carts = user.getCarts();
            carts.forEach(cart -> responses.add(cart.mapToResponse()));
            return responses;
        }
    }

    @Override
    public CartResponse getOneByUser(String username, Integer id) {
        ValidationUtil.checkId(id, cartRepository);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));

        if (user.getCarts().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            return user.getCarts().stream()
                    .filter(cart -> cart.getId().equals(id))
                    .findFirst()
                    .orElseThrow(DbResponseErrorException::new)
                    .mapToResponse();
        }
    }

    @Override
    public Cart getByUserId(Integer userId) {
        ValidationUtil.checkId(userId, userRepository);
        return cartRepository.findByUserId(userId).orElseThrow(DbResponseErrorException::new);
    }

}
