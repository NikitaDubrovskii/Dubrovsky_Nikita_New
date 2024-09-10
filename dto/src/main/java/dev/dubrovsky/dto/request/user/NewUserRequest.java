package dev.dubrovsky.dto.request.user;

public record NewUserRequest(

        String username,

        String password,

        String email

) {
}
