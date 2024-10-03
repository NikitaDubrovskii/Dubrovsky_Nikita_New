package dev.dubrovsky.dto.request.payment.method;

import jakarta.validation.constraints.NotEmpty;

public record UpdatePaymentMethodRequest(

        @NotEmpty(message = "Method не может быть пустым")
        String method

) {
}
