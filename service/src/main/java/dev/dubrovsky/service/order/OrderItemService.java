package dev.dubrovsky.service.order;

import dev.dubrovsky.model.order.OrderItem;
import dev.dubrovsky.repository.order.OrderItemRepository;
import dev.dubrovsky.repository.order.OrderRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService implements IOrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderItem create(OrderItem orderItem) {
        validateOrderItem(orderItem);
        ValidationUtil.checkEntityPresent(orderItem.getOrder().getId(), orderRepository);
        ValidationUtil.checkEntityPresent(orderItem.getProduct().getId(), productRepository);

        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getById(Integer id) {
        ValidationUtil.checkId(id, orderItemRepository);

        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderItem> getAll() {
        if (orderItemRepository.findAll().isEmpty()) {
            return null;
        } else {
            return orderItemRepository.findAll();
        }
    }

    @Override
    public OrderItem update(OrderItem orderItem, Integer id) {
        validateOrderItem(orderItem);
        ValidationUtil.checkEntityPresent(orderItem.getOrder().getId(), orderRepository);
        ValidationUtil.checkEntityPresent(orderItem.getProduct().getId(), productRepository);
        ValidationUtil.checkId(id, orderItemRepository);

        orderItem.setId(id);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, orderItemRepository);
        orderItemRepository.deleteById(id);

        return "Удалено!";
    }

    private void validateOrderItem(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("Предмет в заказе не может отсутствовать");
        }
        if (orderItem.getQuantity() == null || orderItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Количество не может отсутствовать");
        }
    }

}
