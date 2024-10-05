package dev.dubrovsky.dto.request.bonus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBonusRequest{

    String name;

    String description;

    Integer points;

    Integer programId;

}
