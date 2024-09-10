package dev.dubrovsky.dto.request.order;

public record UpdateOrderRequest(

        Float totalPrice,

        String address,

        Integer paymentMethodId,

        Integer userId

) {
}
