package dev.dubrovsky.dto.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание нового заказа")
public class NewOrderRequest {

    @NotNull(message = "TotalPrice не может отсутствовать")
    @Positive(message = "TotalPrice должен быть больше 0.00")
    @Schema(description = "Общая стоимость", example = "123.45")
    Float totalPrice;

    @NotBlank(message = "Address не может отсутствовать")
    @Schema(description = "Адрес", example = "г. Минск")
    String address;

    @NotNull(message = "PaymentMethodId не может отсутствовать")
    @Positive(message = "PaymentMethodId должен быть больше 0")
    @Schema(description = "Id способа оплаты", example = "1")
    Integer paymentMethodId;

    @NotNull(message = "UserId не может отсутствовать")
    @Positive(message = "UserId должен быть больше 0")
    @Schema(description = "Id пользователя", example = "1")
    Integer userId;

}
