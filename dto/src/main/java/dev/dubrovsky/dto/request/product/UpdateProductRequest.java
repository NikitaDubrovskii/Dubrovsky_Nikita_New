package dev.dubrovsky.dto.request.product;

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
public class UpdateProductRequest {

    @NotEmpty(message = "Name не может быть пустым")
    String name;

    String description;

    @NotEmpty(message = "Price не может быть пустым")
    @Positive(message = "Price должен быть больше 0")
    Float price;

    @NotEmpty(message = "CategoryId не может быть пустым")
    @Positive(message = "CategoryId должен быть больше 0")
    Integer categoryId;

}
