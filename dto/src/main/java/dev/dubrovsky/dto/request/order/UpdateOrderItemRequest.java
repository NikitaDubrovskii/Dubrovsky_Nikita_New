package dev.dubrovsky.dto.request.order;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderItemRequest {

    @Positive(message = "Quantity должно быть больше 0")
    Integer quantity;

    @Positive(message = "OrderId должен быть больше 0")
    Integer orderId;

    @Positive(message = "ProductId должен быть больше 0")
    Integer productId;

}
