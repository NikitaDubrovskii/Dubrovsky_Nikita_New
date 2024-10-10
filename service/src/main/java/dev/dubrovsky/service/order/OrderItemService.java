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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService implements IOrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Override
    public void create(NewOrderItemRequest request) {
        ValidationUtil.checkEntityPresent(request.getOrderId(), orderRepository);
        ValidationUtil.checkEntityPresent(request.getProductId(), productRepository);

        OrderItem orderItem = mapper
                .typeMap(NewOrderItemRequest.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId))
                .map(request);
        orderItem.setOrder(orderRepository
                .findById(request.getOrderId())
                .orElseThrow(DbResponseErrorException::new));
        orderItem.setProduct(productRepository
                .findById(request.getProductId())
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

        if (request.getQuantity() != null && request.getQuantity() > 0) {
            orderItem.setQuantity(request.getQuantity());
        }
        if (request.getProductId() != null && request.getProductId() > 0) {
            ValidationUtil.checkEntityPresent(request.getProductId(), productRepository);
            orderItem.setProduct(productRepository.findById(request.getProductId()).orElseThrow(DbResponseErrorException::new));
        }
        if (request.getOrderId() != null && request.getOrderId() > 0) {
            ValidationUtil.checkEntityPresent(request.getOrderId(), orderRepository);
            orderItem.setOrder(orderRepository.findById(request.getOrderId()).orElseThrow(DbResponseErrorException::new));
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
