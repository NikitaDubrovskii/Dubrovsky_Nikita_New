package dev.dubrovsky.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(

        @NotBlank(message = "UsernameOrEmail не может быть пустым")
        String usernameOrEmail,

        @NotBlank(message = "Password не может отсутствовать")
        String password

) {
}
