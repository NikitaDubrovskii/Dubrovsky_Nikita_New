package dev.dubrovsky.controller.order;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.order.NewOrderItemRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderItemRequest;
import dev.dubrovsky.dto.response.order.OrderItemResponse;
import dev.dubrovsky.service.order.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractOrderItemController extends AbstractController<OrderItemService, OrderItemResponse, NewOrderItemRequest, UpdateOrderItemRequest> {

    public AbstractOrderItemController(OrderItemService service) {
        super(service);
    }

    @GetMapping("/my/{orderId}")
    public abstract ResponseEntity<?> getOrderItemsByUser(Authentication authentication, @PathVariable Integer orderId);

    @GetMapping("/my/{orderId}/{itemId}")
    public abstract ResponseEntity<?> getOneByUser(Authentication authentication, @PathVariable Integer orderId, @PathVariable Integer itemId);

}
