package dev.dubrovsky.dto.request.product;

public record UpdateProductRequest(

        String name,

        String description,

        Float price,

        Integer categoryId

) {
}
