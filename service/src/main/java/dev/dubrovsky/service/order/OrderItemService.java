package dev.dubrovsky.service.order;

import dev.dubrovsky.dao.order.IOrderDao;
import dev.dubrovsky.dao.order.IOrderItemDao;
import dev.dubrovsky.dao.product.IProductDao;
import dev.dubrovsky.model.order.OrderItem;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderItemService implements IOrderItemService {

    private final IOrderItemDao orderItemDao;
    private final IOrderDao orderDao;
    private final IProductDao productDao;

    public OrderItemService(IOrderItemDao orderItemDao, IOrderDao orderDao, IProductDao productDao) {
        this.orderItemDao = orderItemDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    @Override
    public void create(OrderItem orderItem) {
        validateOrderItem(orderItem);
        checkOrderPresent(orderItem.getOrderId());
        checkProductPresent(orderItem.getProductId());

        orderItemDao.create(orderItem);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

        System.out.println(orderItemDao.getById(id));
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
    public void update(OrderItem orderItem, Integer id) {
        validateOrderItem(orderItem);
        checkOrderPresent(orderItem.getOrderId());
        checkProductPresent(orderItem.getProductId());
        checkId(id);

        orderItem.setId(id);
        orderItemDao.update(orderItem);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        orderItemDao.delete(id);
    }

    private void validateOrderItem(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("Предмет в заказе не может отсутствовать");
        }
        if (orderItem.getQuantity() == null || orderItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Количество не может отсутствовать");
        }
    }

    private void checkOrderPresent(Integer orderId) {
        if (orderId > 0) {
            Optional
                    .ofNullable(orderDao.getById(orderId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + orderId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkProductPresent(Integer productId) {
        if (productId > 0) {
            Optional
                    .ofNullable(productDao.getById(productId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + productId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(orderItemDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
