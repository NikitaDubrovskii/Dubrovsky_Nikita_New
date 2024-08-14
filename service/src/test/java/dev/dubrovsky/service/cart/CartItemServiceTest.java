package dev.dubrovsky.service.cart;

import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartItemServiceTest {

    @Mock
    private CartItemDao cartItemDao;

    @Mock
    private CartDao cartDao;

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private CartItemService cartItemService;

    private CartItem cartItem;
    private CartItem updCartItem;

    @BeforeEach
    void setUp() {
        cartItemService = new CartItemService(cartItemDao, cartDao, productDao);

        cartItem = new CartItem(2, 1, 1);
        cartItem.setId(1);
        updCartItem = new CartItem(1, 2, 2);
    }

    @Test
    void create_Success() {
        when(cartDao.getById(cartItem.getCartId())).thenReturn(new Cart());
        when(productDao.getById(cartItem.getProductId())).thenReturn(new Product());

        cartItemService.create(cartItem);

        verify(cartItemDao).create(cartItem);
    }

    @Test
    void create_CartItemIsNull_ThrowIllegalArgumentException() {
        cartItem = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Предмет в корзине не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_QuantityIsNull_ThrowIllegalArgumentException() {
        cartItem.setQuantity(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Количество не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_QuantityIsZero_ThrowIllegalArgumentException() {
        cartItem.setQuantity(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Количество не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_CartIdIsNull_ThrowIllegalArgumentException() {
        cartItem.setCartId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartItemService.create(cartItem));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", exception.getMessage());
    }

    @Test
    void create_CartNotFound_ThrowNoSuchElementException() {
        when(cartDao.getById(cartItem.getCartId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartItemService.create(cartItem));
        assertEquals("Ничего не найдено с id: " + cartItem.getCartId(), exception.getMessage());
    }

    @Test
    void create_CartIdIsNegative_ThrowIllegalArgumentException() {
        cartItem.setCartId(-1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_CartIdIsZero_ThrowIllegalArgumentException() {
        cartItem.setCartId(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_ProductIdIsNull_ThrowIllegalArgumentException() {
        when(cartDao.getById(cartItem.getCartId())).thenReturn(new Cart());
        cartItem.setProductId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartItemService.create(cartItem));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", exception.getMessage());
    }

    @Test
    void create_ProductNotFound_ThrowNoSuchElementException() {
        when(cartDao.getById(cartItem.getCartId())).thenReturn(new Cart());
        when(productDao.getById(cartItem.getProductId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartItemService.create(cartItem));
        assertEquals("Ничего не найдено с id: " + cartItem.getProductId(), exception.getMessage());
    }

    @Test
    void create_ProductIdIsNegative_ThrowIllegalArgumentException() {
        when(cartDao.getById(cartItem.getCartId())).thenReturn(new Cart());
        cartItem.setProductId(-1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_ProductIdIsZero_ThrowIllegalArgumentException() {
        when(cartDao.getById(cartItem.getCartId())).thenReturn(new Cart());
        cartItem.setProductId(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(cartItemDao.getById(id)).thenReturn(cartItem);

        cartItemService.getById(id);

        verify(cartItemDao, times(2)).getById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> cartItemService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(cartItemDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartItemService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        CartItem cartItem2 = new CartItem(2, 1, 1);
        cartItem2.setId(2);
        List<CartItem> cartItemList = List.of(
                cartItem,
                cartItem2
        );

        when(cartItemDao.getAll()).thenReturn(cartItemList);

        cartItemService.getAll();

        verify(cartItemDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(cartItemDao.getAll()).thenReturn(Collections.emptyList());

        cartItemService.getAll();

        verify(cartItemDao, times(3)).getAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        when(productDao.getById(updCartItem.getProductId())).thenReturn(new Product());
        when(cartItemDao.getById(id)).thenReturn(cartItem);

        cartItemService.update(updCartItem, id);

        verify(cartItemDao).update(updCartItem);
    }

    @Test
    void update_CartItemIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCartItem = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Предмет в корзине не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_QuantityIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCartItem.setQuantity(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Количество не может отсутствовать", exception.getMessage());
    }

    @Test
    void update_QuantityIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCartItem.setQuantity(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Количество не может отсутствовать", exception.getMessage());
    }

    @Test
    void update_CartIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        updCartItem.setCartId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", exception.getMessage());
    }

    @Test
    void update_CartNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Ничего не найдено с id: " + updCartItem.getCartId(), exception.getMessage());
    }

    @Test
    void update_CartIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCartItem.setCartId(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_CartIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCartItem.setCartId(-1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_ProductIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        updCartItem.setProductId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", exception.getMessage());
    }

    @Test
    void update_ProductNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        when(productDao.getById(updCartItem.getProductId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Ничего не найдено с id: " + updCartItem.getProductId(), exception.getMessage());
    }

    @Test
    void update_ProductIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        updCartItem.setProductId(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_ProductIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        updCartItem.setProductId(-1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        when(productDao.getById(updCartItem.getProductId())).thenReturn(new Product());

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        when(productDao.getById(updCartItem.getProductId())).thenReturn(new Product());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        when(productDao.getById(updCartItem.getProductId())).thenReturn(new Product());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(cartDao.getById(updCartItem.getCartId())).thenReturn(new Cart());
        when(productDao.getById(updCartItem.getProductId())).thenReturn(new Product());
        when(cartItemDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(cartItemDao.getById(id)).thenReturn(cartItem);

        cartItemService.delete(id);

        verify(cartItemDao).delete(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> cartItemService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartItemService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

}
