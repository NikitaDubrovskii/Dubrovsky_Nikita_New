package dev.dubrovsky.dto.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание нового продукта")
public class NewProductRequest {

    @NotBlank(message = "Name не может отсутствовать")
    @Schema(description = "Название продукта", example = "Кот")
    String name;

    @Schema(description = "Описание продукта", example = "Кот в мешке")
    String description;

    @NotNull(message = "Price не может отсутствовать")
    @Positive(message = "Price должен быть больше 0")
    @Schema(description = "Цена на продукт", example = "15.00")
    Float price;

    @NotNull(message = "CategoryId не может отсутствовать")
    @Positive(message = "CategoryId должен быть больше 0")
    @Schema(description = "Id категории", example = "2")
    Integer categoryId;

}
