package dev.dubrovsky.dto.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на обновление продукта")
public class UpdateProductRequest {

    @NotEmpty(message = "Name не может быть пустым")
    @Schema(description = "Название продукта", example = "Кот")
    String name;

    @Schema(description = "Описание продукта", example = "Кот в мешке")
    String description;

    @NotEmpty(message = "Price не может быть пустым")
    @Positive(message = "Price должен быть больше 0")
    @Schema(description = "Цена на продукт", example = "15.00")
    Float price;

    @NotEmpty(message = "CategoryId не может быть пустым")
    @Positive(message = "CategoryId должен быть больше 0")
    @Schema(description = "Id категории", example = "2")
    Integer categoryId;

}
