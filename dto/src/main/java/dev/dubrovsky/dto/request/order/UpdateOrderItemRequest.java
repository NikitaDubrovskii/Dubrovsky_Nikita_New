package dev.dubrovsky.dto.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на обновление продукта в заказе")
public class UpdateOrderItemRequest {

    @Positive(message = "Quantity должно быть больше 0")
    @Schema(description = "Количество данного продукта", example = "2")
    Integer quantity;

    @Positive(message = "OrderId должен быть больше 0")
    @Schema(description = "Id заказа", example = "1")
    Integer orderId;

    @Positive(message = "ProductId должен быть больше 0")
    @Schema(description = "Id продукта", example = "1")
    Integer productId;

}
