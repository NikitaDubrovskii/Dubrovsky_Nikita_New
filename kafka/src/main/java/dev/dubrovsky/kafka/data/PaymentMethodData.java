package dev.dubrovsky.kafka.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentMethodData {

    private Integer id;

    private String method;

}
