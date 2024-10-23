package dev.dubrovsky.dto.response.cart;

import dev.dubrovsky.dto.response.product.ProductResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с продуктами в корзине")
public record CartItemResponse(

        @Schema(description = "Идентификатор продукта в корзине", example = "1")
        Integer id,

        @Schema(description = "Количество данного продукта", example = "2")
        Integer quantity,

        @Schema(description = "Информация о корзине")
        CartResponse cart,

        @Schema(description = "Информация о продукте")
        ProductResponse product

) {
}
