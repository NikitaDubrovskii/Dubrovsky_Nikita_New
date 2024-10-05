package dev.dubrovsky.dto.request.order;

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
public class NewOrderItemRequest {

    @NotNull(message = "Quantity не может отсутствовать")
    @Positive(message = "Quantity должно быть больше 0")
    Integer quantity;

    @NotNull(message = "OrderId не может отсутствовать")
    @Positive(message = "OrderId должен быть больше 0")
    Integer orderId;

    @NotNull(message = "ProductId не может отсутствовать")
    @Positive(message = "ProductId должен быть больше 0")
    Integer productId;

}
