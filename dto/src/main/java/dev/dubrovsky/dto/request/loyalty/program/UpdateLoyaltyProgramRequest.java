package dev.dubrovsky.dto.request.loyalty.program;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLoyaltyProgramRequest {

    String name;

    String description;

}
