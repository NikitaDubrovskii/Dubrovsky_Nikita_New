package dev.dubrovsky.service.order;

import dev.dubrovsky.dao.order.OrderDao;
import dev.dubrovsky.dao.payment.method.PaymentMethodDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.util.validation.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    private final OrderDao orderDao;
    private final PaymentMethodDao paymentMethodDao;
    private final UserDao userDao;

    public OrderService(OrderDao orderDao, PaymentMethodDao paymentMethodDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.paymentMethodDao = paymentMethodDao;
        this.userDao = userDao;
    }

    @Override
    public void create(Order order) {
        validateOrder(order);
        ValidationUtil.checkEntityPresent(order.getUser().getId(), userDao);
        ValidationUtil.checkEntityPresent(order.getPaymentMethod().getId(), paymentMethodDao);

        orderDao.create(order);
    }

    @Override
    public Order getById(Integer id) {
        ValidationUtil.checkId(id, orderDao);

        return orderDao.getById(id);
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
        ValidationUtil.checkEntityPresent(order.getUser().getId(), userDao);
        ValidationUtil.checkEntityPresent(order.getPaymentMethod().getId(), paymentMethodDao);
        ValidationUtil.checkId(id, orderDao);

        order.setId(id);
        orderDao.update(order);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, orderDao);

        orderDao.delete(id);
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Заказ не может отсутствовать");
        }
        if (order.getTotalPrice() == null || order.getTotalPrice() < 1) {
            throw new IllegalArgumentException("Цена не может быть пустой");
        }
        if (order.getAddress() == null || order.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Адрес не может быть пустой");
        }
    }

}
