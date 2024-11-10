package dev.dubrovsky.dto.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Запрос на обновление заказа")
public class UpdateOrderRequest {

    @NotNull(message = "TotalPrice не может быть пустым")
    @Positive(message = "TotalPrice должен быть больше 0.00")
    @Schema(description = "Общая стоимость", example = "123.45")
    Float totalPrice;

    @Schema(description = "Адрес", example = "г. Минск")
    String address;

    @Positive(message = "PaymentMethodId должен быть больше 0")
    @Schema(description = "Id способа оплаты", example = "1")
    Integer paymentMethodId;

    @Positive(message = "UserId должен быть больше 0")
    @Schema(description = "Id пользователя", example = "1")
    Integer userId;

}
