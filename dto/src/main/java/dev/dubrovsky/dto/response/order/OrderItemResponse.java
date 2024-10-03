package dev.dubrovsky.dto.response.order;

import dev.dubrovsky.dto.response.product.ProductResponse;

public record OrderItemResponse(

        Integer id,

        Integer quantity,

        OrderResponse order,

        ProductResponse product

) {
}
