package dev.dubrovsky.dto.response.product;

import dev.dubrovsky.dto.response.category.CategoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ с продуктом")
public record ProductResponse(

        @Schema(description = "Идентификатор продукта", example = "1")
        Integer id,

        @Schema(description = "Название продукта", example = "Кот")
        String name,

        @Schema(description = "Описание продукта", example = "Кот в мешке")
        String description,

        @Schema(description = "Цена на продукт", example = "15.00")
        Float price,

        @Schema(description = "Время создания", example = "2024-08-10T16:43:53.145856")
        LocalDateTime createdAt,

        @Schema(description = "Информация о категории")
        CategoryResponse category

) {
}
