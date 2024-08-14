package dev.dubrovsky.dao.order;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.order.Order;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    public OrderDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Order.class);
    }

}
