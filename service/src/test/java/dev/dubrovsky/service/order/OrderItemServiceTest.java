package dev.dubrovsky.service.order;

import dev.dubrovsky.dao.order.OrderDao;
import dev.dubrovsky.dao.order.OrderItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.model.order.OrderItem;
import dev.dubrovsky.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceTest {

    @Mock
    private OrderItemDao orderItemDao;

    @Mock
    private OrderDao orderDao;

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private OrderItemService orderItemService;

    private OrderItem orderItem;
    private OrderItem updOrderItem;
    private Order order1;
    private Order order2;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        orderItemService = new OrderItemService(orderItemDao, orderDao, productDao);

        order1 = new Order();
        order1.setId(1);
        order2 = new Order();
        order2.setId(2);
        product1 = new Product();
        product1.setId(1);
        product2 = new Product();
        product2.setId(2);

        orderItem = new OrderItem(2, order1, product1);
        orderItem.setId(1);
        updOrderItem = new OrderItem(2, order2, product2);
    }

    @Test
    void create_Success() {
        when(orderDao.getById(orderItem.getOrder().getId())).thenReturn(new Order());
        when(productDao.getById(orderItem.getProduct().getId())).thenReturn(new Product());

        orderItemService.create(orderItem);

        verify(orderItemDao).create(orderItem);
    }

    @Test
    void create_OrderItemIsNull_ThrowIllegalArgumentException() {
        orderItem = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.create(orderItem));
        assertEquals("Предмет в заказе не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_QuantityIsNull_ThrowIllegalArgumentException() {
        orderItem.setQuantity(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.create(orderItem));
        assertEquals("Количество не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_QuantityIsZero_ThrowIllegalArgumentException() {
        orderItem.setQuantity(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.create(orderItem));
        assertEquals("Количество не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_OrderIdIsNull_ThrowIllegalArgumentException() {
        orderItem.setOrder(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderItemService.create(orderItem));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.order.Order.getId()\" because the return value of \"dev.dubrovsky.model.order.OrderItem.getOrder()\" is null", exception.getMessage());
    }

    @Test
    void create_OrderNotFound_ThrowNoSuchElementException() {
        when(orderDao.getById(orderItem.getOrder().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> orderItemService.create(orderItem));
        assertEquals("Ничего не найдено с id: " + orderItem.getOrder().getId(), exception.getMessage());
    }

    @Test
    void create_OrderIdIsNegative_ThrowIllegalArgumentException() {
        order1.setId(-1);
        orderItem.setOrder(order1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.create(orderItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_OrderIdIsZero_ThrowIllegalArgumentException() {
        order1.setId(0);
        orderItem.setOrder(order1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.create(orderItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_ProductIdIsNull_ThrowIllegalArgumentException() {
        when(orderDao.getById(orderItem.getOrder().getId())).thenReturn(new Order());
        orderItem.setProduct(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderItemService.create(orderItem));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.product.Product.getId()\" because the return value of \"dev.dubrovsky.model.order.OrderItem.getProduct()\" is null", exception.getMessage());
    }

    @Test
    void create_ProductNotFound_ThrowNoSuchElementException() {
        when(orderDao.getById(orderItem.getOrder().getId())).thenReturn(new Order());
        when(productDao.getById(orderItem.getProduct().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> orderItemService.create(orderItem));
        assertEquals("Ничего не найдено с id: " + orderItem.getProduct().getId(), exception.getMessage());
    }

    @Test
    void create_ProductIdIsNegative_ThrowIllegalArgumentException() {
        when(orderDao.getById(orderItem.getOrder().getId())).thenReturn(new Order());
        product1.setId(-1);
        orderItem.setProduct(product1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.create(orderItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_ProductIdIsZero_ThrowIllegalArgumentException() {
        when(orderDao.getById(orderItem.getOrder().getId())).thenReturn(new Order());
        product1.setId(0);
        orderItem.setProduct(product1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.create(orderItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(orderItemDao.getById(id)).thenReturn(orderItem);

        orderItemService.getById(id);

        verify(orderItemDao, times(2)).getById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> orderItemService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderItemService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderItemService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(orderItemDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> orderItemService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        OrderItem orderItem2 = new OrderItem(2, order2, product2);
        orderItem2.setId(2);
        List<OrderItem> orderItemList = List.of(
                orderItem2, orderItem2
        );

        when(orderItemDao.getAll()).thenReturn(orderItemList);

        orderItemService.getAll();

        verify(orderItemDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(orderItemDao.getAll()).thenReturn(Collections.emptyList());

        orderItemService.getAll();

        verify(orderItemDao, times(3)).getAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        when(productDao.getById(updOrderItem.getProduct().getId())).thenReturn(new Product());
        when(orderItemDao.getById(id)).thenReturn(orderItem);

        orderItemService.update(updOrderItem, id);

        verify(orderItemDao).update(updOrderItem);
    }

    @Test
    void update_OrderItemIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updOrderItem = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Предмет в заказе не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_QuantityIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updOrderItem.setQuantity(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Количество не может отсутствовать", exception.getMessage());
    }

    @Test
    void update_QuantityIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        updOrderItem.setQuantity(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Количество не может отсутствовать", exception.getMessage());
    }

    @Test
    void update_OrderIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        updOrderItem.setOrder(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.order.Order.getId()\" because the return value of \"dev.dubrovsky.model.order.OrderItem.getOrder()\" is null", exception.getMessage());
    }

    @Test
    void update_OrderNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Ничего не найдено с id: " + updOrderItem.getOrder().getId(), exception.getMessage());
    }

    @Test
    void update_OrderIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        order1.setId(0);
        updOrderItem.setOrder(order1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_CartIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        order1.setId(-1);
        updOrderItem.setOrder(order1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_ProductIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        updOrderItem.setProduct(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.product.Product.getId()\" because the return value of \"dev.dubrovsky.model.order.OrderItem.getProduct()\" is null", exception.getMessage());
    }

    @Test
    void update_ProductNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        when(productDao.getById(updOrderItem.getProduct().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Ничего не найдено с id: " + updOrderItem.getProduct().getId(), exception.getMessage());
    }

    @Test
    void update_ProductIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        product1.setId(0);
        updOrderItem.setProduct(product1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_ProductIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        product1.setId(-1);
        updOrderItem.setProduct(product1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        when(productDao.getById(updOrderItem.getProduct().getId())).thenReturn(new Product());

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        when(productDao.getById(updOrderItem.getProduct().getId())).thenReturn(new Product());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        when(productDao.getById(updOrderItem.getProduct().getId())).thenReturn(new Product());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(orderDao.getById(updOrderItem.getOrder().getId())).thenReturn(new Order());
        when(productDao.getById(updOrderItem.getProduct().getId())).thenReturn(new Product());
        when(orderItemDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> orderItemService.update(updOrderItem, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(orderItemDao.getById(id)).thenReturn(orderItem);

        orderItemService.delete(id);

        verify(orderItemDao).delete(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> orderItemService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderItemService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> orderItemService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderItemService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

}
