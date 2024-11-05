package dev.dubrovsky.kafka.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {

    private Integer id;

    private String name;

    private Float price;

}
