package dev.dubrovsky.dao.order;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.order.Order;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    public OrderDao(Class<Order> entityClass) {
        super(entityClass);
    }

}
