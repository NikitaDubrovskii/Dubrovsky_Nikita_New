package dev.dubrovsky.dto.request.cart;

import jakarta.validation.constraints.Positive;

public record UpdateCartItemRequest(

        @Positive(message = "Quantity должно быть больше 0")
        Integer quantity,

        @Positive(message = "CartId должно быть больше 0")
        Integer cartId,

        @Positive(message = "ProductId должно быть больше 0")
        Integer productId

) {
}
