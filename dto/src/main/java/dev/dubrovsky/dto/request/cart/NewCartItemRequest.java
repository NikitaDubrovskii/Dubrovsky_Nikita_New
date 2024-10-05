package dev.dubrovsky.dto.request.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCartItemRequest {

    @NotNull(message = "Quantity не может отсутствовать")
    @Positive(message = "Quantity должно быть больше 0")
    Integer quantity;

    @NotNull(message = "CartId не может отсутствовать")
    @Positive(message = "CartId должно быть больше 0")
    Integer cartId;

    @NotNull(message = "ProductId не может отсутствовать")
    @Positive(message = "ProductId должно быть больше 0")
    Integer productId;

}
