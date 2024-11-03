package dev.dubrovsky.service.order;

import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import dev.dubrovsky.dto.request.order.NewOrderItemRequest;
import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.dto.response.cart.CartItemResponse;
import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.dto.response.order.OrderResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.cart.CartRepository;
import dev.dubrovsky.repository.order.OrderRepository;
import dev.dubrovsky.repository.payment.method.PaymentMethodRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.service.analytics.AnalyticsService;
import dev.dubrovsky.service.bonus.BonusService;
import dev.dubrovsky.service.bonus.UserBonusService;
import dev.dubrovsky.service.cart.CartItemService;
import dev.dubrovsky.service.cart.CartService;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;
    private final CartItemService cartItemService;
    private final CartService cartService;
    private final OrderItemService orderItemService;
    private final AnalyticsService analyticsService;
    private final UserBonusService userBonusService;

    private final ModelMapper mapper;

    @Override
    public void create(NewOrderRequest request) {
        Order order = mapToSaveOrder(request);

        orderRepository.save(order);
    }

    public Integer createReturnId(NewOrderRequest request) {
        Order order = mapToSaveOrder(request);

        Order save = orderRepository.save(order);
        return save.getId();
    }

    private Order mapToSaveOrder(NewOrderRequest request) {
        ValidationUtil.checkEntityPresent(request.getUserId(), userRepository);
        ValidationUtil.checkEntityPresent(request.getPaymentMethodId(), paymentMethodRepository);

        Order order = mapper
                .typeMap(NewOrderRequest.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId))
                .map(request);
        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            order.setAddress(request.getAddress());
        }
        order.setPaymentMethod(paymentMethodRepository
                .findById(request.getPaymentMethodId())
                .orElseThrow(DbResponseErrorException::new));
        order.setUser(userRepository
                .findById(request.getUserId())
                .orElseThrow(DbResponseErrorException::new));
        order.setCreatedAt(LocalDateTime.now());
        return order;
    }

    @Override
    public OrderResponse getById(Integer id) {
        ValidationUtil.checkId(id, orderRepository);

        Order order = orderRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return order.mapToResponse();
    }

    @Override
    public List<OrderResponse> getAll() {
        if (orderRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<OrderResponse> responses = new ArrayList<>();
            List<Order> all = orderRepository.findAll();

            all.forEach(order -> responses.add(order.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateOrderRequest request, Integer id) {
        ValidationUtil.checkId(id, orderRepository);

        Order order = orderRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.getTotalPrice() != null && request.getTotalPrice() > 0) {
            order.setTotalPrice(request.getTotalPrice());
        }
        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            order.setAddress(request.getAddress());
        }
        if (request.getPaymentMethodId() != null && request.getPaymentMethodId() > 0) {
            ValidationUtil.checkEntityPresent(request.getPaymentMethodId(), paymentMethodRepository);
            order.setPaymentMethod(paymentMethodRepository.findById(request.getPaymentMethodId()).orElseThrow(DbResponseErrorException::new));
        }
        if (request.getUserId() != null && request.getUserId() > 0) {
            ValidationUtil.checkEntityPresent(request.getUserId(), userRepository);
            order.setUser(userRepository.findById(request.getUserId()).orElseThrow(DbResponseErrorException::new));
        }
        order.setId(id);

        orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, orderRepository);
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponse> getOrdersByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));

        if (user.getOrders().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<OrderResponse> responses = new ArrayList<>();
            List<Order> orders = user.getOrders();
            orders.forEach(order -> responses.add(order.mapToResponse()));
            return responses;
        }
    }

    @Override
    public OrderResponse getOneByUser(String username, Integer id) {
        ValidationUtil.checkId(id, orderRepository);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));

        if (user.getOrders().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            return user.getOrders().stream()
                    .filter(order -> order.getId().equals(id))
                    .findFirst()
                    .orElseThrow(DbResponseErrorException::new)
                    .mapToResponse();
        }
    }

    @Override
    public Order getByUserId(Integer userId) {
        ValidationUtil.checkId(userId, userRepository);
        return orderRepository.findByUserId(userId).orElseThrow(DbResponseErrorException::new);
    }

    @Override
    public void createOrder(String username, Integer cartId, NewOrderRequest newOrderRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));

        List<CartItemResponse> cartItems = cartItemService.getCartItemsByUser(username, cartId);
        newOrderRequest.setUserId(user.getId());
        newOrderRequest.setTotalPrice(cartService.getTotalPrice(cartId));
        Integer newOrderId = createReturnId(newOrderRequest);

        for (CartItemResponse cartItem : cartItems) {
            NewOrderItemRequest newOrderItemRequest = new NewOrderItemRequest(cartItem.quantity(), newOrderId, cartItem.product().id());
            orderItemService.create(newOrderItemRequest);
        }

        analyticsService.create(new NewAnalyticsRequest("Сделан заказ №" + cartId, user.getId()));
        userBonusService.create(new NewUserBonusRequest(user.getId(), 3));
    }

}
