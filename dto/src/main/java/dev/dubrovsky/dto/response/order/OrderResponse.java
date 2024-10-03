package dev.dubrovsky.dto.response.order;

import dev.dubrovsky.dto.response.payment.method.PaymentMethodResponse;
import dev.dubrovsky.dto.response.user.UserResponse;

import java.time.LocalDateTime;

public record OrderResponse(

        Integer id,

        Float totalPrice,

        LocalDateTime createdAt,

        String address,

        PaymentMethodResponse paymentMethod,

        UserResponse user

) {
}
