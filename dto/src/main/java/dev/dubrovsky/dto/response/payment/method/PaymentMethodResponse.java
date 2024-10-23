package dev.dubrovsky.dto.response.payment.method;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ со способом оплаты")
public record PaymentMethodResponse(

        @Schema(description = "Идентификатор аналитики", example = "1")
        Integer id,

        @Schema(description = "Метод оплаты", example = "Карта")
        String method

) {
}
