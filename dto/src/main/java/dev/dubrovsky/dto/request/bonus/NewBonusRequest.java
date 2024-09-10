package dev.dubrovsky.dto.request.bonus;

public record NewBonusRequest(

        String name,

        String description,

        Integer points,

        Integer programId

) {
}
