package dev.dubrovsky.kafka;

import dev.dubrovsky.kafka.data.OrderData;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderData> kafkaTemplate;

    public void sendOrder(String topic, OrderData orderData) {
        kafkaTemplate.send(topic, orderData);
        System.out.println("Отправлен заказ в тему " + topic + ": " + orderData);
    }

}
