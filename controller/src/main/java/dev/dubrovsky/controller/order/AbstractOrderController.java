package dev.dubrovsky.controller.order;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.dto.response.order.OrderResponse;
import dev.dubrovsky.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractOrderController extends AbstractController<OrderService, OrderResponse, NewOrderRequest, UpdateOrderRequest> {

    public AbstractOrderController(OrderService service) {
        super(service);
    }

    @GetMapping("/my")
    public abstract ResponseEntity<?> getOrdersByUser(Authentication authentication);

    @GetMapping("/my/{id}")
    public abstract ResponseEntity<?> getOneByUser(Authentication authentication, @PathVariable Integer id);

}
