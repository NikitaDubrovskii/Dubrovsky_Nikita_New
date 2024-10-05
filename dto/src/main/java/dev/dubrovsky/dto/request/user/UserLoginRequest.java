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
public class UserLoginRequest {

    @NotBlank(message = "UsernameOrEmail не может быть пустым")
    String usernameOrEmail;

    @NotBlank(message = "Password не может отсутствовать")
    String password;

}
