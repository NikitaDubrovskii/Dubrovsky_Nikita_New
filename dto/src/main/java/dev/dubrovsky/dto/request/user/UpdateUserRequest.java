package dev.dubrovsky.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на обновление пользователя")
public class UpdateUserRequest {

    @NotEmpty(message = "Username не может быть пустым")
    @Schema(description = "Ник/имя пользователя", example = "Jon")
    String username;

    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Email должен соответствовать виду email")
    @Schema(description = "Электронная почта пользователя", example = "jon@gmail.com")
    String email;

}
