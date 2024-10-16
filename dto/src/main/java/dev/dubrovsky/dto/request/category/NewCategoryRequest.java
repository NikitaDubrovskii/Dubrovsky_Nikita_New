package dev.dubrovsky.dto.request.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание новой категории")
public class NewCategoryRequest {

    @NotBlank(message = "Name не может отсутствовать")
    @Schema(description = "Название категории", example = "Товары для дома")
    String name;

    @Schema(description = "Описание категории", example = "Товары для дома и не только")
    String description;

}
