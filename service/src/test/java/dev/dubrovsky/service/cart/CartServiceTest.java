package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.user.User;
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
class CartServiceTest {

    @Mock
    private CartDao cartDao;

    @Mock
    private UserDao userDao;

    @Mock
    private CartItemDao cartItemDao;

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private CartService cartService;

    private Cart cart;
    private Cart updCart;
    private User user;

    @BeforeEach
    void setUp() {
        cartService = new CartService(cartDao, userDao, cartItemDao, productDao);

        user = new User();
        user.setId(1);

        cart = new Cart();
        cart.setId(1);
        cart.setUser(user);
        updCart = new Cart();
        updCart.setUser(user);
    }

    @Test
    void create() {
        when(userDao.getById(cart.getUser().getId())).thenReturn(new User());

        cartService.create(cart);

        verify(cartDao).create(cart);
    }

    @Test
    void create_CartIsNull_ThrowIllegalArgumentException() {
        cart = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.create(cart));
        assertEquals("Корзина не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_UserIdIsNull_ThrowNullPointerException() {
        cart.setUser(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartService.create(cart));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.user.User.getId()\" because the return value of \"dev.dubrovsky.model.cart.Cart.getUser()\" is null", exception.getMessage());
    }

    @Test
    void create_UserNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(cart.getUser().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartService.create(cart));
        assertEquals("Ничего не найдено с id: " + cart.getUser().getId(), exception.getMessage());
    }

    @Test
    void create_UserIdIsNegative_ThrowIllegalArgumentException() {
        user.setId(-1);
        cart.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.create(cart));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_UserIdIsZero_ThrowIllegalArgumentException() {
        user.setId(0);
        cart.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.create(cart));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(cartDao.getById(id)).thenReturn(cart);

        cartService.getById(id);

        verify(cartDao, times(2)).getById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> cartService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());

    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(cartDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        Cart cart2 = new Cart();
        cart2.setId(2);
        List<Cart> cartList = List.of(
                cart,
                cart2
        );

        when(cartDao.getAll()).thenReturn(cartList);

        cartService.getAll();

        verify(cartDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(cartDao.getAll()).thenReturn(Collections.emptyList());

        cartService.getAll();

        verify(cartDao, times(3)).getAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(userDao.getById(updCart.getUser().getId())).thenReturn(new User());
        when(cartDao.getById(id)).thenReturn(cart);

        cartService.update(updCart, id);

        verify(cartDao).update(updCart);
    }

    @Test
    void update_CartIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCart = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.update(updCart, id));
        assertEquals("Корзина не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_UserIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        updCart.setUser(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartService.update(updCart, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.user.User.getId()\" because the return value of \"dev.dubrovsky.model.cart.Cart.getUser()\" is null", exception.getMessage());
    }

    @Test
    void update_UserIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        user.setId(0);
        updCart.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.update(updCart, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_UserNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(userDao.getById(updCart.getUser().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartService.update(updCart, id));
        assertEquals("Ничего не найдено с id: " + updCart.getUser().getId(), exception.getMessage());
    }

    @Test
    void update_UserIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        user.setId(-1);
        updCart.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartService.update(updCart, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;
        when(userDao.getById(updCart.getUser().getId())).thenReturn(new User());

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> cartService.update(updCart, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;
        when(userDao.getById(updCart.getUser().getId())).thenReturn(new User());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.update(updCart, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;
        when(userDao.getById(updCart.getUser().getId())).thenReturn(new User());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.update(updCart, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(cartDao.getById(id)).thenReturn(null);
        when(userDao.getById(updCart.getUser().getId())).thenReturn(new User());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartService.update(updCart, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(cartDao.getById(id)).thenReturn(cart);

        cartService.delete(id);

        verify(cartDao).delete(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> cartService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(cartDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

}
