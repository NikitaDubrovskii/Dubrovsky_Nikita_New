package dev.dubrovsky.service.order;

import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.repository.order.OrderRepository;
import dev.dubrovsky.repository.payment.method.PaymentMethodRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;

    @Override
    public Order create(Order order) {
        validateOrder(order);
        ValidationUtil.checkEntityPresent(order.getUser().getId(), userRepository);
        ValidationUtil.checkEntityPresent(order.getPaymentMethod().getId(), paymentMethodRepository);

        return orderRepository.save(order);
    }

    @Override
    public Order getById(Integer id) {
        ValidationUtil.checkId(id, orderRepository);

        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAll() {
        if (orderRepository.findAll().isEmpty()) {
            return null;
        } else {
            return orderRepository.findAll();
        }
    }

    @Override
    public Order update(Order order, Integer id) {
        validateOrder(order);
        ValidationUtil.checkEntityPresent(order.getUser().getId(), userRepository);
        ValidationUtil.checkEntityPresent(order.getPaymentMethod().getId(), paymentMethodRepository);
        ValidationUtil.checkId(id, orderRepository);

        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, orderRepository);
        orderRepository.deleteById(id);

        return "Удалено!";
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
