package dev.dubrovsky.dto.request.payment.method;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPaymentMethodRequest {

    @NotBlank(message = "Method не может отсутствовать")
    String method;

}
