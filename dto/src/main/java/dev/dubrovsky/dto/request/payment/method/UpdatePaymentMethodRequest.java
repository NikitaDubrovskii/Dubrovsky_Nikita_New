package dev.dubrovsky.dto.request.payment.method;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание нового способа оплаты")
public class UpdatePaymentMethodRequest {

    @NotEmpty(message = "Method не может быть пустым")
    @Schema(description = "Метод оплаты", example = "Карта")
    String method;

}
