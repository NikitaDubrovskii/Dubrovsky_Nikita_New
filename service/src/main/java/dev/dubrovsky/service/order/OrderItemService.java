package dev.dubrovsky.service.order;

import dev.dubrovsky.dto.request.order.NewOrderItemRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderItemRequest;
import dev.dubrovsky.dto.response.order.OrderItemResponse;
import dev.dubrovsky.model.order.OrderItem;
import dev.dubrovsky.repository.order.OrderItemRepository;
import dev.dubrovsky.repository.order.OrderRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService implements IOrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public void create(NewOrderItemRequest request) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(request.quantity());
        orderItem.setOrder(orderRepository
                .findById(request.orderId())
                .orElse(null));
        orderItem.setProduct(productRepository
                .findById(request.productId())
                .orElse(null));

        validateOrderItem(orderItem);
        ValidationUtil.checkEntityPresent(orderItem.getOrder().getId(), orderRepository);
        ValidationUtil.checkEntityPresent(orderItem.getProduct().getId(), productRepository);

        orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItemResponse getById(Integer id) {
        ValidationUtil.checkId(id, orderItemRepository);

        OrderItem orderItem = orderItemRepository.findById(id).orElse(null);
        return orderItem.mapToResponse();
    }

    @Override
    public List<OrderItemResponse> getAll() {
        if (orderItemRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<OrderItemResponse> responses = new ArrayList<>();
            List<OrderItem> all = orderItemRepository.findAll();

            all.forEach(orderItem -> responses.add(orderItem.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateOrderItemRequest request, Integer id) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(request.quantity());
        orderItem.setOrder(orderRepository
                .findById(request.orderId())
                .orElse(null));
        orderItem.setProduct(productRepository
                .findById(request.productId())
                .orElse(null));

        validateOrderItem(orderItem);
        ValidationUtil.checkEntityPresent(orderItem.getOrder().getId(), orderRepository);
        ValidationUtil.checkEntityPresent(orderItem.getProduct().getId(), productRepository);
        ValidationUtil.checkId(id, orderItemRepository);

        orderItem.setId(id);
        orderItemRepository.save(orderItem);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, orderItemRepository);
        orderItemRepository.deleteById(id);
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
