package dev.dubrovsky.dto.request.cart;

public record UpdateCartItemRequest(

        Integer quantity,

        Integer cartId,

        Integer productId

) {
}
