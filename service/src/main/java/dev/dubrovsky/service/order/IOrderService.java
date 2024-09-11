package dev.dubrovsky.service.order;

import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.dto.response.order.OrderResponse;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.order.Order;

public interface IOrderService extends ICommonService<OrderResponse, NewOrderRequest, UpdateOrderRequest> {
}
