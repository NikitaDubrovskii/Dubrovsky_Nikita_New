package dev.dubrovsky.dto.response.cart;

import dev.dubrovsky.dto.response.product.ProductResponse;

public record CartItemResponse(

        Integer id,

        Integer quantity,

        CartResponse cart,

        ProductResponse product

) {
}
