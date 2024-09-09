package dev.dubrovsky.service.cart;

import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.cart.CartItemRepository;
import dev.dubrovsky.repository.cart.CartRepository;
import dev.dubrovsky.repository.product.ProductRepository;
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
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;
    private Cart updCart;
    private User user;

    @BeforeEach
    void setUp() {
        cartService = new CartService(cartRepository, userRepository, cartItemRepository, productRepository);

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
        when(userRepository.findById(cart.getUser().getId())).thenReturn(Optional.of(new User()));

        cartService.create(cart);

        verify(cartRepository).save(cart);
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
        when(userRepository.findById(cart.getUser().getId())).thenReturn(Optional.empty());

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
        when(cartRepository.findById(id)).thenReturn(Optional.of(cart));

        cartService.getById(id);

        verify(cartRepository, times(2)).findById(id);
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
        when(cartRepository.findById(id)).thenReturn(Optional.empty());

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

        when(cartRepository.findAll()).thenReturn(cartList);

        cartService.getAll();

        verify(cartRepository, times(2)).findAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(cartRepository.findAll()).thenReturn(Collections.emptyList());

        cartService.getAll();

        verify(cartRepository, times(1)).findAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(userRepository.findById(updCart.getUser().getId())).thenReturn(Optional.of(new User()));
        when(cartRepository.findById(id)).thenReturn(Optional.of(cart));

        cartService.update(updCart, id);

        verify(cartRepository).save(updCart);
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
        when(userRepository.findById(updCart.getUser().getId())).thenReturn(Optional.empty());

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
        when(userRepository.findById(updCart.getUser().getId())).thenReturn(Optional.of(new User()));

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> cartService.update(updCart, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;
        when(userRepository.findById(updCart.getUser().getId())).thenReturn(Optional.of(new User()));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.update(updCart, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;
        when(userRepository.findById(updCart.getUser().getId())).thenReturn(Optional.of(new User()));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartService.update(updCart, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(cartRepository.findById(id)).thenReturn(Optional.empty());
        when(userRepository.findById(updCart.getUser().getId())).thenReturn(Optional.of(new User()));

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartService.update(updCart, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(cartRepository.findById(id)).thenReturn(Optional.of(cart));

        cartService.delete(id);

        verify(cartRepository).deleteById(id);
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
        when(cartRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

}
