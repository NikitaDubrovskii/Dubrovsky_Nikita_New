package dev.dubrovsky.dto.response.cart;

import dev.dubrovsky.dto.response.user.UserResponse;

import java.time.LocalDateTime;

public record CartResponse(

        Integer id,

        LocalDateTime createdAt,

        UserResponse user

) {
}
