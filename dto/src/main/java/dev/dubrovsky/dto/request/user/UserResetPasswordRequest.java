package dev.dubrovsky.dto.request.user;

public record UserResetPasswordRequest(

        String usernameOrEmail,

        String oldPassword,

        String newPassword

) {
}
