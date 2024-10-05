package dev.dubrovsky.dto.request.loyalty.program;

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
public class NewUserLoyaltyProgramRequest {

    @NotNull(message = "UserId не может отсутствовать")
    @Positive(message = "UserId должен быть больше 0")
    Integer userId;

    @NotNull(message = "ProgramId не может отсутствовать")
    @Positive(message = "ProgramId должен быть больше 0")
    Integer programId;

}
