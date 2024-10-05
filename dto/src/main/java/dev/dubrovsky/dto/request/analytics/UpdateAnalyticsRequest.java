package dev.dubrovsky.dto.request.analytics;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAnalyticsRequest {

    String activity;

    @Positive(message = "UserId должен быть больше 0")
    Integer userId;

}
