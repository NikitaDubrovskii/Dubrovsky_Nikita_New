package dev.dubrovsky.kafka.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemData {

    private Integer id;

    private Integer quantity;

    private ProductData productData;

}
