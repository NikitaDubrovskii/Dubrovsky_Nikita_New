package dev.dubrovsky.dto.request.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record UpdateOrderRequest(

        @NotEmpty(message = "TotalPrice не может быть пустым")
        @Positive(message = "TotalPrice должен быть больше 0.00")
        Float totalPrice,

        String address,

        @Positive(message = "PaymentMethodId должен быть больше 0")
        Integer paymentMethodId,

        @Positive(message = "UserId должен быть больше 0")
        Integer userId

) {
}
