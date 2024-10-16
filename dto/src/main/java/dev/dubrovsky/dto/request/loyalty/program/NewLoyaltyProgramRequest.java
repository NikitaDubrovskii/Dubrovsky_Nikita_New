package dev.dubrovsky.dto.request.loyalty.program;

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
@Schema(description = "Запрос на создание новой программы лояльности")
public class NewLoyaltyProgramRequest {

    @NotBlank(message = "Name не может отсутствовать")
    @Schema(description = "Название программы лояльности", example = "Золото 1")
    String name;

    @Schema(description = "Описание программы лояльности", example = "Уровень Золото 1")
    String description;

}
