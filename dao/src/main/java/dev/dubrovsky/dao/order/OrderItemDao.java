package dev.dubrovsky.dao.order;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.order.OrderItem;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDao extends AbstractDao<OrderItem> implements IOrderItemDao {

    public OrderItemDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, OrderItem.class);
    }

}
