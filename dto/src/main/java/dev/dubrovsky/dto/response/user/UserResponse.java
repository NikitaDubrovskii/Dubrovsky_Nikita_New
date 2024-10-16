package dev.dubrovsky.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с пользователем")
public record UserResponse(

        @Schema(description = "Идентификатор пользователя", example = "1")
        Integer id,

        @Schema(description = "Ник/имя пользователя", example = "Jon")
        String username,

        @Schema(description = "Электронная почта пользователя", example = "jon@gmail.com")
        String email

) {
}
