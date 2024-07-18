package dev.dubrovsky.dao.order;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.order.OrderItem;

public class OrderItemDao extends AbstractDao<OrderItem> implements IOrderItemDao {

    public OrderItemDao(Class<OrderItem> entityClass) {
        super(entityClass);
    }

}
