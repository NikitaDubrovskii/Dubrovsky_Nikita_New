package dev.dubrovsky.kafka.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    private Integer id;

    private Float totalPrice;

    private LocalDateTime createdAt;

    private String address;

    private PaymentMethodData paymentMethodData;

    private UserData userData;

    private List<OrderItemData> orderItemsData;

}
