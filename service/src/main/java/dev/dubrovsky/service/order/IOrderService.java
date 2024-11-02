package dev.dubrovsky.service.order;

import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.dto.response.order.OrderResponse;
import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.service.ICommonService;

import java.util.List;

public interface IOrderService extends ICommonService<OrderResponse, NewOrderRequest, UpdateOrderRequest> {

    List<OrderResponse> getOrdersByUser(String username);

    OrderResponse getOneByUser(String username, Integer id);

    Order getByUserId(Integer id);

}
