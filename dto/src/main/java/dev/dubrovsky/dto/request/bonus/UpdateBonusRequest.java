package dev.dubrovsky.dto.request.bonus;

public record UpdateBonusRequest(

        String name,

        String description,

        Integer points,

        Integer programId

) {
}
