package dev.dubrovsky.dto.request.cart;

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
@Schema(description = "Запрос на обновление продукта в корзине")
public class UpdateCartItemRequest {

    @Positive(message = "Quantity должно быть больше 0")
    @Schema(description = "Количество данного продукта", example = "2")
    Integer quantity;

    @Positive(message = "CartId должно быть больше 0")
    @Schema(description = "Id корзины", example = "1")
    Integer cartId;

    @Positive(message = "ProductId должно быть больше 0")
    @Schema(description = "Id продукта", example = "1")
    Integer productId;

}
