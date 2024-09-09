package dev.dubrovsky.service.order;

import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.order.OrderRepository;
import dev.dubrovsky.repository.payment.method.PaymentMethodRepository;
import dev.dubrovsky.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private Order updOrder;
    private PaymentMethod paymentMethod1;
    private PaymentMethod paymentMethod2;
    private User user;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, paymentMethodRepository, userRepository);

        paymentMethod1 = new PaymentMethod();
        paymentMethod1.setId(1);
        paymentMethod2 = new PaymentMethod();
        paymentMethod2.setId(2);
        user = new User();
        user.setId(1);

        order = new Order(100, "test", paymentMethod1, user);
        order.setId(1);
        updOrder = new Order(200, "upd", paymentMethod2, user);
    }

    @Test
    void create_Success() {
        when(userRepository.findById(order.getUser().getId())).thenReturn(Optional.of(new User()));
        when(paymentMethodRepository.findById(order.getPaymentMethod().getId())).thenReturn(Optional.of(new PaymentMethod()));

        orderService.create(order);

        verify(orderRepository).save(order);
    }

    @Test
    void create_OrderIsNull_ThrowIllegalArgumentException() {
        order = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Заказ не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_TotalPriseIsNull_ThrowIllegalArgumentException() {
        order.setTotalPrice(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Цена не может быть пустой", exception.getMessage());
    }

    @Test
    void create_TotalPriseIsZero_ThrowIllegalArgumentException() {
        order.setTotalPrice(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Цена не может быть пустой", exception.getMessage());
    }

    @Test
    void create_AddressIsNull_ThrowIllegalArgumentException() {
        order.setAddress(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Адрес не может быть пустой", exception.getMessage());
    }

    @Test
    void create_AddressIsEmpty_ThrowIllegalArgumentException() {
        order.setAddress(" ");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Адрес не может быть пустой", exception.getMessage());
    }

    @Test
    void create_UserIdIsNull_ThrowIllegalArgumentException() {
        order.setUser(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderService.create(order));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.user.User.getId()\" because the return value of \"dev.dubrovsky.model.order.Order.getUser()\" is null", exception.getMessage());
    }

    @Test
    void create_UserNotFound_ThrowNoSuchElementException() {
        when(userRepository.findById(order.getUser().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> orderService.create(order));
        assertEquals("Ничего не найдено с id: " + order.getUser().getId(), exception.getMessage());
    }

    @Test
    void create_UserIdIsNegative_ThrowIllegalArgumentException() {
        user.setId(-1);
        order.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_UserIdIsZero_ThrowIllegalArgumentException() {
        user.setId(0);
        order.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_PaymentMethodIdIsNull_ThrowIllegalArgumentException() {
        when(userRepository.findById(order.getUser().getId())).thenReturn(Optional.of(new User()));
        order.setPaymentMethod(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderService.create(order));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.payment.method.PaymentMethod.getId()\" because the return value of \"dev.dubrovsky.model.order.Order.getPaymentMethod()\" is null", exception.getMessage());
    }

    @Test
    void create_PaymentMethodNotFound_ThrowNoSuchElementException() {
        when(userRepository.findById(order.getUser().getId())).thenReturn(Optional.of(new User()));
        when(paymentMethodRepository.findById(order.getPaymentMethod().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> orderService.create(order));
        assertEquals("Ничего не найдено с id: " + order.getPaymentMethod().getId(), exception.getMessage());
    }

    @Test
    void create_PaymentMethodIdIsNegative_ThrowIllegalArgumentException() {
        when(userRepository.findById(order.getUser().getId())).thenReturn(Optional.of(new User()));
        paymentMethod1.setId(-1);
        order.setPaymentMethod(paymentMethod1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_PaymentMethodIdIsZero_ThrowIllegalArgumentException() {
        when(userRepository.findById(order.getUser().getId())).thenReturn(Optional.of(new User()));
        paymentMethod1.setId(0);
        order.setPaymentMethod(paymentMethod1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.create(order));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        orderService.getById(id);

        verify(orderRepository, times(2)).findById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> orderService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> orderService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        Order order2 = new Order(100, "test", paymentMethod2, user);
        order2.setId(2);
        List<Order> orderList = List.of(
                order,
                order2
        );

        when(orderRepository.findAll()).thenReturn(orderList);

        orderService.getAll();

        verify(orderRepository, times(2)).findAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());

        orderService.getAll();

        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        when(paymentMethodRepository.findById(updOrder.getPaymentMethod().getId())).thenReturn(Optional.of(new PaymentMethod()));
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        orderService.update(updOrder, id);

        verify(orderRepository).save(updOrder);
    }

    @Test
    void update_OrderIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updOrder = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Заказ не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_TotalPriseIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updOrder.setTotalPrice(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Цена не может быть пустой", exception.getMessage());
    }

    @Test
    void update_TotalPriseIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        updOrder.setTotalPrice(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Цена не может быть пустой", exception.getMessage());
    }

    @Test
    void update_AddressIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updOrder.setAddress(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Адрес не может быть пустой", exception.getMessage());
    }

    @Test
    void update_AddressIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        updOrder.setAddress(" ");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Адрес не может быть пустой", exception.getMessage());
    }

    @Test
    void update_UserIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        updOrder.setUser(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderService.update(updOrder, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.user.User.getId()\" because the return value of \"dev.dubrovsky.model.order.Order.getUser()\" is null", exception.getMessage());
    }

    @Test
    void update_UserNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> orderService.update(updOrder, id));
        assertEquals("Ничего не найдено с id: " + updOrder.getUser().getId(), exception.getMessage());
    }

    @Test
    void update_UserIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        user.setId(-1);
        updOrder.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_UserIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        user.setId(0);
        updOrder.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_PaymentMethodIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        updOrder.setPaymentMethod(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> orderService.update(updOrder, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.payment.method.PaymentMethod.getId()\" because the return value of \"dev.dubrovsky.model.order.Order.getPaymentMethod()\" is null", exception.getMessage());
    }

    @Test
    void update_PaymentMethodNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        when(paymentMethodRepository.findById(updOrder.getPaymentMethod().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> orderService.update(updOrder, id));
        assertEquals("Ничего не найдено с id: " + updOrder.getPaymentMethod().getId(), exception.getMessage());
    }

    @Test
    void update_PaymentMethodIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        paymentMethod1.setId(0);
        updOrder.setPaymentMethod(paymentMethod1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_ProductIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        paymentMethod1.setId(-1);
        updOrder.setPaymentMethod(paymentMethod1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        when(paymentMethodRepository.findById(updOrder.getPaymentMethod().getId())).thenReturn(Optional.of(new PaymentMethod()));

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> orderService.update(updOrder, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        when(paymentMethodRepository.findById(updOrder.getPaymentMethod().getId())).thenReturn(Optional.of(new PaymentMethod()));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        when(paymentMethodRepository.findById(updOrder.getPaymentMethod().getId())).thenReturn(Optional.of(new PaymentMethod()));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderService.update(updOrder, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(userRepository.findById(updOrder.getUser().getId())).thenReturn(Optional.of(new User()));
        when(paymentMethodRepository.findById(updOrder.getPaymentMethod().getId())).thenReturn(Optional.of(new PaymentMethod()));
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> orderService.update(updOrder, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        orderService.delete(id);

        verify(orderRepository).deleteById(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> orderService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> orderService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> orderService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

}
