package dev.dubrovsky.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с обычным текстом")
public record SimpleTextResponse(

        @Schema(description = "Ответ", example = "Выполнено!")
        String description

) {
}
