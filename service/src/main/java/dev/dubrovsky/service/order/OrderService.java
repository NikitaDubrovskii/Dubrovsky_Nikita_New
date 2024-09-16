package dev.dubrovsky.service.order;

import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.dto.response.order.OrderResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
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
        ValidationUtil.checkEntityPresent(request.userId(), userRepository);
        ValidationUtil.checkEntityPresent(request.paymentMethodId(), paymentMethodRepository);

        Order order = new Order();
        order.setTotalPrice(request.totalPrice());
        if (request.address() != null && !request.address().isEmpty()) {
            order.setAddress(request.address());
        }
        order.setPaymentMethod(paymentMethodRepository
                .findById(request.paymentMethodId())
                .orElseThrow(DbResponseErrorException::new));
        order.setUser(userRepository
                .findById(request.userId())
                .orElseThrow(DbResponseErrorException::new));
        order.setCreatedAt(LocalDateTime.now());

        orderRepository.save(order);
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

        if (request.totalPrice() != null && request.totalPrice() > 0) {
            order.setTotalPrice(request.totalPrice());
        }
        if (request.address() != null && !request.address().isEmpty()) {
            order.setAddress(request.address());
        }
        if (request.paymentMethodId() != null && request.paymentMethodId() > 0) {
            ValidationUtil.checkEntityPresent(request.paymentMethodId(), paymentMethodRepository);
            order.setPaymentMethod(paymentMethodRepository.findById(request.paymentMethodId()).orElseThrow(DbResponseErrorException::new));
        }
        if (request.userId() != null && request.userId() > 0) {
            ValidationUtil.checkEntityPresent(request.userId(), userRepository);
            order.setUser(userRepository.findById(request.userId()).orElseThrow(DbResponseErrorException::new));
        }
        order.setId(id);

        orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, orderRepository);
        orderRepository.deleteById(id);
    }

}
