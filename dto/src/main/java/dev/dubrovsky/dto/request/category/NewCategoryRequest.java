package dev.dubrovsky.dto.request.category;

import jakarta.validation.constraints.NotBlank;

public record NewCategoryRequest(

        @NotBlank(message = "Name не может отсутствовать")
        String name,

        String description

) {
}
