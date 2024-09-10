package dev.dubrovsky.dto.request.order;

public record NewOrderItemRequest(

        Integer quantity,

        Integer orderId,

        Integer productId

) {
}
