package dev.dubrovsky.dto.request.user;

public record UserLoginRequest(

        String usernameOrEmail,

        String password

) {
}
