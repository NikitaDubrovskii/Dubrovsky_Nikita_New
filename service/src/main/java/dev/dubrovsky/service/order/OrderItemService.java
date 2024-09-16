package dev.dubrovsky.service.order;

import dev.dubrovsky.dto.request.order.NewOrderItemRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderItemRequest;
import dev.dubrovsky.dto.response.order.OrderItemResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
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
        ValidationUtil.checkEntityPresent(request.orderId(), orderRepository);
        ValidationUtil.checkEntityPresent(request.productId(), productRepository);

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(request.quantity());
        orderItem.setOrder(orderRepository
                .findById(request.orderId())
                .orElseThrow(DbResponseErrorException::new));
        orderItem.setProduct(productRepository
                .findById(request.productId())
                .orElseThrow(DbResponseErrorException::new));

        orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItemResponse getById(Integer id) {
        ValidationUtil.checkId(id, orderItemRepository);

        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return orderItem.mapToResponse();
    }

    @Override
    public List<OrderItemResponse> getAll() {
        if (orderItemRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<OrderItemResponse> responses = new ArrayList<>();
            List<OrderItem> all = orderItemRepository.findAll();

            all.forEach(orderItem -> responses.add(orderItem.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateOrderItemRequest request, Integer id) {
        ValidationUtil.checkId(id, orderItemRepository);

        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.quantity() != null && request.quantity() > 0) {
            orderItem.setQuantity(request.quantity());
        }
        if (request.productId() != null && request.productId() > 0) {
            ValidationUtil.checkEntityPresent(request.productId(), productRepository);
            orderItem.setProduct(productRepository.findById(request.productId()).orElseThrow(DbResponseErrorException::new));
        }
        if (request.orderId() != null && request.orderId() > 0) {
            ValidationUtil.checkEntityPresent(request.orderId(), orderRepository);
            orderItem.setOrder(orderRepository.findById(request.orderId()).orElseThrow(DbResponseErrorException::new));
        }
        orderItem.setId(id);

        orderItemRepository.save(orderItem);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, orderItemRepository);
        orderItemRepository.deleteById(id);
    }

}
