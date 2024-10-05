package dev.dubrovsky.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCategoryRequest {

    @NotBlank(message = "Name не может отсутствовать")
    String name;

    String description;

}
