package dev.dubrovsky.service.order;

import dev.dubrovsky.dao.order.IOrderItemDao;
import dev.dubrovsky.model.order.OrderItem;

public class OrderItemService implements IOrderItemService {

    private final IOrderItemDao orderItemDao;

    public OrderItemService(IOrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Override
    public void create(OrderItem entity) {
        orderItemDao.create(entity);
    }

    @Override
    public void getAll() {
        if (orderItemDao.getAll().isEmpty() && orderItemDao.getAll() == null) {
            System.out.println("Таблица вещей в заказе пустая");
        } else {
            orderItemDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(OrderItem entity) {
        orderItemDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            orderItemDao.delete(id);
        }
    }

}
