package dev.dubrovsky.dto.request.order;

public record NewOrderRequest(

        Float totalPrice,

        String address,

        Integer paymentMethodId,

        Integer userId

) {
}
