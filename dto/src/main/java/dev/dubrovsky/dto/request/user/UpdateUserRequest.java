package dev.dubrovsky.dto.request.user;

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
public class UpdateUserRequest {

    @NotEmpty(message = "Username не может быть пустым")
    String username;

    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Email должен соответствовать виду email")
    String email;

}
