package dev.dubrovsky.service.order;

import dev.dubrovsky.dao.order.IOrderDao;
import dev.dubrovsky.model.order.Order;

public class OrderService implements IOrderService {

    private final IOrderDao orderDao;

    public OrderService(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void create(Order entity) {
        orderDao.create(entity);
    }

    @Override
    public void getAll() {
        if (orderDao.getAll().isEmpty() && orderDao.getAll() == null) {
            System.out.println("Таблица заказов пустая");
        } else {
            orderDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(Order entity) {
        orderDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            orderDao.delete(id);
        }
    }

}
