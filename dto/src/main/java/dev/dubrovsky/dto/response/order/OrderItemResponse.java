package dev.dubrovsky.dto.response.order;

import dev.dubrovsky.dto.response.product.ProductResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с продуктом в заказе")
public record OrderItemResponse(

        @Schema(description = "Идентификатор продукта в заказе", example = "1")
        Integer id,

        @Schema(description = "Количество данного продукта", example = "2")
        Integer quantity,

        @Schema(description = "Информация о заказе")
        OrderResponse order,

        @Schema(description = "Информация о продукте")
        ProductResponse product

) {
}
