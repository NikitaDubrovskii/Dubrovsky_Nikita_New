package dev.dubrovsky.dto.request.order;

public record UpdateOrderItemRequest(

        Integer quantity,

        Integer orderId,

        Integer productId

){
}
