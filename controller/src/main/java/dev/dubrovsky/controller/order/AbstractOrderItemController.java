package dev.dubrovsky.controller.order;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.order.NewOrderItemRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderItemRequest;
import dev.dubrovsky.dto.response.order.OrderItemResponse;
import dev.dubrovsky.service.order.OrderItemService;

public abstract class AbstractOrderItemController extends AbstractController<OrderItemService, OrderItemResponse, NewOrderItemRequest, UpdateOrderItemRequest> {

    public AbstractOrderItemController(OrderItemService service) {
        super(service);
    }

}
