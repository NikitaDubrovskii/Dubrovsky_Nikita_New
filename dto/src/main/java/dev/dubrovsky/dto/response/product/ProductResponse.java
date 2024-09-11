package dev.dubrovsky.dto.response.product;

import dev.dubrovsky.dto.response.category.CategoryResponse;

import java.time.LocalDateTime;

public record ProductResponse(

        Integer id,

        String name,

        String description,

        Float price,

        LocalDateTime createdAt,

        CategoryResponse category

) {
}
