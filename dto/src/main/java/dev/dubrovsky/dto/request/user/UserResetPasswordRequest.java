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
@Schema(description = "Запрос на сброс пароля")
public class UserResetPasswordRequest {

    @NotBlank(message = "UsernameOrEmail не может быть пустым")
    @Schema(description = "Юзернэйм или электронная почта", example = "jon@gmail.com")
    String usernameOrEmail;

    @NotBlank(message = "OldPassword не может отсутствовать")
    @Schema(description = "Старый пароль пользователя", example = "jon1234")
    String oldPassword;

    @NotBlank(message = "NewPassword не может отсутствовать")
    @Schema(description = "Новый пароль пользователя", example = "1234jon")
    String newPassword;

}
