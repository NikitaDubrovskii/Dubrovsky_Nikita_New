package dev.dubrovsky.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на авторизацию")
public class UserLoginRequest {

    @NotBlank(message = "UsernameOrEmail не может быть пустым")
    @Schema(description = "Юзернэйм или электронная почта", example = "jon@gmail.com")
    String usernameOrEmail;

    @NotBlank(message = "Password не может отсутствовать")
    @Schema(description = "Пароль пользователя", example = "jon1234")
    String password;

}
