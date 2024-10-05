package dev.dubrovsky.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResetPasswordRequest {

    @NotBlank(message = "UsernameOrEmail не может быть пустым")
    String usernameOrEmail;

    @NotBlank(message = "OldPassword не может отсутствовать")
    String oldPassword;

    @NotBlank(message = "NewPassword не может отсутствовать")
    String newPassword;

}
