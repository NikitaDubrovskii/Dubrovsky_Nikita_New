package dev.dubrovsky.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record UserResetPasswordRequest(

        @NotBlank(message = "UsernameOrEmail не может быть пустым")
        String usernameOrEmail,

        @NotBlank(message = "OldPassword не может отсутствовать")
        String oldPassword,

        @NotBlank(message = "NewPassword не может отсутствовать")
        String newPassword

) {
}
