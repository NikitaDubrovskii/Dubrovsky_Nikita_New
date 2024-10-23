package dev.dubrovsky.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание нового пользователя")
public class NewUserRequest {

    @NotBlank(message = "Username не может отсутствовать")
    @Schema(description = "Ник/имя пользователя", example = "Jon")
    String username;

    @NotBlank(message = "Password не может отсутствовать")
    @Schema(description = "Пароль пользователя", example = "jon1234")
    String password;

    @NotBlank(message = "Email не может отсутствовать")
    @Email(message = "Email должен соответствовать виду email")
    @Schema(description = "Электронная почта пользователя", example = "jon@gmail.com")
    String email;

}
