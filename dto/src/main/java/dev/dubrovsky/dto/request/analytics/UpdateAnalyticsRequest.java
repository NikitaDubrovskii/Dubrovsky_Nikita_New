package dev.dubrovsky.dto.request.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на обновление аналитики")
public class UpdateAnalyticsRequest {

    @Schema(description = "Активность", example = "Подписка")
    String activity;

    @Positive(message = "UserId должен быть больше 0")
    @Schema(description = "Id пользователя", example = "1")
    Integer userId;

}
