package dev.dubrovsky.dto.request.payment.method;

import jakarta.validation.constraints.NotBlank;

public record NewPaymentMethodRequest(

        @NotBlank(message = "Method не может отсутствовать")
        String method

) {
}
