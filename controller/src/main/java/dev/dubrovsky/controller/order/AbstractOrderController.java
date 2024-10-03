package dev.dubrovsky.controller.order;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.dto.response.order.OrderResponse;
import dev.dubrovsky.service.order.OrderService;

public abstract class AbstractOrderController extends AbstractController<OrderService, OrderResponse, NewOrderRequest, UpdateOrderRequest> {

    public AbstractOrderController(OrderService service) {
        super(service);
    }

}
