package dev.dubrovsky.dto.request.user;

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
public class NewUserRequest {

    @NotBlank(message = "Username не может отсутствовать")
    String username;

    @NotBlank(message = "Password не может отсутствовать")
    String password;

    @NotBlank(message = "Email не может отсутствовать")
    @Email(message = "Email должен соответствовать виду email")
    String email;

}
