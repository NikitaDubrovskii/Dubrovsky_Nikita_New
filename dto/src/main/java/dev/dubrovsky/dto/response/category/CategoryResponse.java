package dev.dubrovsky.dto.response.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с категорией")
public record CategoryResponse(

        @Schema(description = "Идентификатор категории", example = "1")
        Integer id,

        @Schema(description = "Название категории", example = "Товары для дома")
        String name,

        @Schema(description = "Описание категории", example = "Товары для дома и не только")
        String description

) {
}
