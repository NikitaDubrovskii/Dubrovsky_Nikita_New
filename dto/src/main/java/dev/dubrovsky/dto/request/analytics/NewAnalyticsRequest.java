package dev.dubrovsky.dto.request.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на создание новой аналитики")
public class NewAnalyticsRequest {

    @NotBlank(message = "Activity не может отсутствовать")
    @Schema(description = "Активность", example = "Подписка")
    String activity;

    @NotNull(message = "UserId не может отсутствовать")
    @Positive(message = "UserId должен быть больше 0")
    @Schema(description = "Id пользователя", example = "1")
    Integer userId;

}
