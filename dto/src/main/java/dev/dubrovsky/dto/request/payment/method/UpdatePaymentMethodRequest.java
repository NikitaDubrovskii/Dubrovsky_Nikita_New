package dev.dubrovsky.dto.request.payment.method;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentMethodRequest {

    @NotEmpty(message = "Method не может быть пустым")
    String method;

}
