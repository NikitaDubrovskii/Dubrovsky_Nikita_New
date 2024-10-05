package dev.dubrovsky.dto.request.loyalty.program;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewLoyaltyProgramRequest {

    @NotBlank(message = "Name не может отсутствовать")
    String name;

    String description;

}
