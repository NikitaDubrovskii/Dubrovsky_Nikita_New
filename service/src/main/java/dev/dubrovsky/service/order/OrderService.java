package dev.dubrovsky.service.order;

import dev.dubrovsky.dao.order.IOrderDao;
import dev.dubrovsky.dao.payment_method.IPaymentMethodDao;
import dev.dubrovsky.dao.user.IUserDao;
import dev.dubrovsky.model.order.Order;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderService implements IOrderService {

    private final IOrderDao orderDao;
    private final IPaymentMethodDao paymentMethodDao;
    private final IUserDao userDao;

    public OrderService(IOrderDao orderDao, IPaymentMethodDao paymentMethodDao, IUserDao userDao) {
        this.orderDao = orderDao;
        this.paymentMethodDao = paymentMethodDao;
        this.userDao = userDao;
    }

    @Override
    public void create(Order order) {
        validateOrder(order);
        checkUserPresent(order.getUserId());
        checkPaymentMethodPresent(order.getPaymentMethodId());

        orderDao.create(order);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

        System.out.println(orderDao.getById(id));
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
    public void update(Order order, Integer id) {
        validateOrder(order);
        checkUserPresent(order.getUserId());
        checkPaymentMethodPresent(order.getPaymentMethodId());
        checkId(id);

        order.setId(id);
        orderDao.update(order);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        orderDao.delete(id);
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Заказ не может отсутствовать");
        }
        if (order.getTotalPrice() == null || order.getTotalPrice() < 0) {
            throw new IllegalArgumentException("Цена не может быть пустой");
        }
        if (order.getAddress() == null || order.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Адрес не может быть пустой");
        }
    }

    private void checkPaymentMethodPresent(Integer paymentMethodId) {
        if (paymentMethodId > 0) {
            Optional
                    .ofNullable(paymentMethodDao.getById(paymentMethodId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + paymentMethodId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkUserPresent(Integer userId) {
        if (userId > 0) {
            Optional
                    .ofNullable(userDao.getById(userId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + userId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(orderDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
