package dev.dubrovsky.service.order;

import dev.dubrovsky.dao.order.OrderDao;
import dev.dubrovsky.dao.order.OrderItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.order.OrderItem;
import dev.dubrovsky.util.validation.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {

    private final OrderItemDao orderItemDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderItemService(OrderItemDao orderItemDao, OrderDao orderDao, ProductDao productDao) {
        this.orderItemDao = orderItemDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        validateOrderItem(orderItem);
        ValidationUtil.checkEntityPresent(orderItem.getOrder().getId(), orderDao);
        ValidationUtil.checkEntityPresent(orderItem.getProduct().getId(), productDao);

        return orderItemDao.create(orderItem);
    }

    @Override
    public OrderItem getById(Integer id) {
        ValidationUtil.checkId(id, orderItemDao);

        return orderItemDao.getById(id);
    }

    @Override
    public List<OrderItem> getAll() {
        if (orderItemDao.getAll().isEmpty() && orderItemDao.getAll() == null) {
            return null;
        } else {
            return orderItemDao.getAll();
        }
    }

    @Override
    public OrderItem update(OrderItem orderItem, Integer id) {
        validateOrderItem(orderItem);
        ValidationUtil.checkEntityPresent(orderItem.getOrder().getId(), orderDao);
        ValidationUtil.checkEntityPresent(orderItem.getProduct().getId(), productDao);
        ValidationUtil.checkId(id, orderItemDao);

        orderItem.setId(id);
        return orderItemDao.update(orderItem);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, orderItemDao);

        return orderItemDao.delete(id);
    }

    private void validateOrderItem(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("Предмет в заказе не может отсутствовать");
        }
        if (orderItem.getQuantity() == null || orderItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Количество не может отсутствовать");
        }
    }

}
