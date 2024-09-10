package dev.dubrovsky.dto.request.product;

public record NewProductRequest(

        String name,

        String description,

        Float price,

        Integer categoryId

) {
}
