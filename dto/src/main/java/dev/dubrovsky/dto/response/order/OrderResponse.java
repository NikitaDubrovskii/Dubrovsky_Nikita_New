package dev.dubrovsky.dto.response.order;

import dev.dubrovsky.dto.response.payment.method.PaymentMethodResponse;
import dev.dubrovsky.dto.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ с заказом")
public record OrderResponse(

        @Schema(description = "Идентификатор заказа", example = "1")
        Integer id,

        @Schema(description = "Общая стоимость", example = "123.45")
        Float totalPrice,

        @Schema(description = "Время создания", example = "2024-08-10T16:43:53.145856")
        LocalDateTime createdAt,

        @Schema(description = "Адрес", example = "г. Минск")
        String address,

        @Schema(description = "Информация о способе оплаты")
        PaymentMethodResponse paymentMethod,

        @Schema(description = "Информация о пользователе")
        UserResponse user

) {
}
