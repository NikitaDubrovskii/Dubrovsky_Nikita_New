package dev.dubrovsky.dto.request.product;

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
public class NewProductRequest {

    @NotBlank(message = "Name не может отсутствовать")
    String name;

    String description;

    @NotNull(message = "Price не может отсутствовать")
    @Positive(message = "Price должен быть больше 0")
    Float price;

    @NotNull(message = "CategoryId не может отсутствовать")
    @Positive(message = "CategoryId должен быть больше 0")
    Integer categoryId;

}
