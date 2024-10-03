package dev.dubrovsky.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UpdateUserRequest(

        @NotEmpty(message = "Username не может быть пустым")
        String username,

        @NotEmpty(message = "Email не может быть пустым")
        @Email(message = "Email должен соответствовать виду email")
        String email

) {
}
