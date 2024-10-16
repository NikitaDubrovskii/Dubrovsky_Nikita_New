package dev.dubrovsky.dto.request.loyalty.program;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Запрос на выдачу программы лояльности для пользователя")
public class NewUserLoyaltyProgramRequest {

    @NotNull(message = "UserId не может отсутствовать")
    @Positive(message = "UserId должен быть больше 0")
    @Schema(description = "Id пользователя, кому выдана программа лояльности", example = "1")
    Integer userId;

    @NotNull(message = "ProgramId не может отсутствовать")
    @Positive(message = "ProgramId должен быть больше 0")
    @Schema(description = "Id программы лояльности, который выдается пользователю", example = "2")
    Integer programId;

}
