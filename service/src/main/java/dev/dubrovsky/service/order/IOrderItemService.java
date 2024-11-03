package dev.dubrovsky.service.order;

import dev.dubrovsky.dto.request.order.NewOrderItemRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderItemRequest;
import dev.dubrovsky.dto.response.order.OrderItemResponse;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.order.OrderItem;

import java.util.List;

public interface IOrderItemService extends ICommonService<OrderItemResponse, NewOrderItemRequest, UpdateOrderItemRequest> {

    List<OrderItemResponse> getOrderItemsByUser(String username, Integer orderId);

    OrderItemResponse getOneByUser(String username, Integer orderId, Integer itemId);

}
