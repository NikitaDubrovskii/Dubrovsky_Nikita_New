package dev.dubrovsky.dto.request.cart;

public record NewCartItemRequest(

        Integer quantity,

        Integer cartId,

        Integer productId

) {
}
