package dev.dubrovsky.dto.request.order;

import jakarta.validation.constraints.NotBlank;
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
public class NewOrderRequest {

    @NotNull(message = "TotalPrice не может отсутствовать")
    @Positive(message = "TotalPrice должен быть больше 0.00")
    Float totalPrice;

    @NotBlank(message = "Address не может отсутствовать")
    String address;

    @NotNull(message = "PaymentMethodId не может отсутствовать")
    @Positive(message = "PaymentMethodId должен быть больше 0")
    Integer paymentMethodId;

    @NotNull(message = "UserId не может отсутствовать")
    @Positive(message = "UserId должен быть больше 0")
    Integer userId;

}
