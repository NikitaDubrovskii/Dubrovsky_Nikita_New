package dev.dubrovsky.dto.request.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateCartRequest(

        @NotNull(message = "UserId не может отсутствовать")
        @Positive(message = "UserId должен быть больше 0")
        Integer userId

) {
}
