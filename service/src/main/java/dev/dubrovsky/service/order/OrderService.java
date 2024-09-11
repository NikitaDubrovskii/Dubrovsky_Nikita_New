package dev.dubrovsky.service.order;

import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.dto.response.order.OrderResponse;
import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.repository.order.OrderRepository;
import dev.dubrovsky.repository.payment.method.PaymentMethodRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
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

    @Override
    public void create(NewOrderRequest request) {
        Order order = new Order();
        order.setTotalPrice(request.totalPrice());
        order.setAddress(request.address());
        order.setPaymentMethod(paymentMethodRepository
                .findById(request.paymentMethodId())
                .orElse(null));
        order.setUser(userRepository
                .findById(request.userId())
                .orElse(null));
        order.setCreatedAt(LocalDateTime.now());


        validateOrder(order);
        ValidationUtil.checkEntityPresent(order.getUser().getId(), userRepository);
        ValidationUtil.checkEntityPresent(order.getPaymentMethod().getId(), paymentMethodRepository);

        orderRepository.save(order);
    }

    @Override
    public OrderResponse getById(Integer id) {
        ValidationUtil.checkId(id, orderRepository);

        Order order = orderRepository.findById(id).orElse(null);
        return order.mapToResponse();
    }

    @Override
    public List<OrderResponse> getAll() {
        if (orderRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<OrderResponse> responses = new ArrayList<>();
            List<Order> all = orderRepository.findAll();

            all.forEach(order -> responses.add(order.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateOrderRequest request, Integer id) {
        Order order = new Order();
        order.setTotalPrice(request.totalPrice());
        order.setAddress(request.address());
        order.setPaymentMethod(paymentMethodRepository
                .findById(request.paymentMethodId())
                .orElse(null));
        order.setUser(userRepository
                .findById(request.userId())
                .orElse(null));

        validateOrder(order);
        ValidationUtil.checkEntityPresent(order.getUser().getId(), userRepository);
        ValidationUtil.checkEntityPresent(order.getPaymentMethod().getId(), paymentMethodRepository);
        ValidationUtil.checkId(id, orderRepository);

        order.setId(id);
        orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, orderRepository);
        orderRepository.deleteById(id);
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Заказ не может отсутствовать");
        }
        if (order.getTotalPrice() == null || order.getTotalPrice() < 1) {
            throw new IllegalArgumentException("Цена не может быть пустой");
        }
        if (order.getAddress() == null || order.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Адрес не может быть пустой");
        }
    }

}
