package dev.dubrovsky.service.cart;

import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.repository.cart.CartItemRepository;
import dev.dubrovsky.repository.cart.CartRepository;
import dev.dubrovsky.repository.product.ProductRepository;
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
class CartItemServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartItemService cartItemService;

    private CartItem cartItem;
    private CartItem updCartItem;
    private Cart cart1;
    private Cart cart2;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        cartItemService = new CartItemService(cartItemRepository, cartRepository, productRepository);

        cart1 = new Cart();
        cart1.setId(1);
        cart2 = new Cart();
        cart2.setId(2);
        product1 = new Product();
        product1.setId(1);
        product2 = new Product();
        product2.setId(2);

        cartItem = new CartItem(2, cart1, product1);
        cartItem.setId(1);
        updCartItem = new CartItem(1, cart2, product2);
    }

    @Test
    void create_Success() {
        when(cartRepository.findById(cartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(cartItem.getProduct().getId())).thenReturn(Optional.of(new Product()));

        cartItemService.create(cartItem);

        verify(cartItemRepository).save(cartItem);
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
        cartItem.setCart(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartItemService.create(cartItem));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.cart.Cart.getId()\" because the return value of \"dev.dubrovsky.model.cart.CartItem.getCart()\" is null", exception.getMessage());
    }

    @Test
    void create_CartNotFound_ThrowNoSuchElementException() {
        when(cartRepository.findById(cartItem.getCart().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartItemService.create(cartItem));
        assertEquals("Ничего не найдено с id: " + cartItem.getCart().getId(), exception.getMessage());
    }

    @Test
    void create_CartIdIsNegative_ThrowIllegalArgumentException() {
        cart1.setId(-1);
        cartItem.setCart(cart1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_CartIdIsZero_ThrowIllegalArgumentException() {
        cart1.setId(0);
        cartItem.setCart(cart1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_ProductIdIsNull_ThrowIllegalArgumentException() {
        when(cartRepository.findById(cartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        cartItem.setProduct(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartItemService.create(cartItem));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.product.Product.getId()\" because the return value of \"dev.dubrovsky.model.cart.CartItem.getProduct()\" is null", exception.getMessage());
    }

    @Test
    void create_ProductNotFound_ThrowNoSuchElementException() {
        when(cartRepository.findById(cartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(cartItem.getProduct().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartItemService.create(cartItem));
        assertEquals("Ничего не найдено с id: " + cartItem.getProduct().getId(), exception.getMessage());
    }

    @Test
    void create_ProductIdIsNegative_ThrowIllegalArgumentException() {
        when(cartRepository.findById(cartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        product1.setId(-1);
        cartItem.setProduct(product1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_ProductIdIsZero_ThrowIllegalArgumentException() {
        when(cartRepository.findById(cartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        product1.setId(0);
        cartItem.setProduct(product1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.create(cartItem));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(cartItemRepository.findById(id)).thenReturn(Optional.of(cartItem));

        cartItemService.getById(id);

        verify(cartItemRepository, times(2)).findById(id);
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
        when(cartItemRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartItemService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {

        CartItem cartItem2 = new CartItem(2, cart2, product1);
        cartItem2.setId(2);
        List<CartItem> cartItemList = List.of(
                cartItem,
                cartItem2
        );

        when(cartItemRepository.findAll()).thenReturn(cartItemList);

        cartItemService.getAll();

        verify(cartItemRepository, times(2)).findAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(cartItemRepository.findAll()).thenReturn(Collections.emptyList());

        cartItemService.getAll();

        verify(cartItemRepository, times(1)).findAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(updCartItem.getProduct().getId())).thenReturn(Optional.of(new Product()));
        when(cartItemRepository.findById(id)).thenReturn(Optional.of(cartItem));

        cartItemService.update(updCartItem, id);

        verify(cartItemRepository).save(updCartItem);
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
        updCartItem.setCart(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.cart.Cart.getId()\" because the return value of \"dev.dubrovsky.model.cart.CartItem.getCart()\" is null", exception.getMessage());
    }

    @Test
    void update_CartNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Ничего не найдено с id: " + updCartItem.getCart().getId(), exception.getMessage());
    }

    @Test
    void update_CartIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        cart1.setId(0);
        updCartItem.setCart(cart1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_CartIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        cart1.setId(-1);
        updCartItem.setCart(cart1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_ProductIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        updCartItem.setProduct(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.product.Product.getId()\" because the return value of \"dev.dubrovsky.model.cart.CartItem.getProduct()\" is null", exception.getMessage());
    }

    @Test
    void update_ProductNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(updCartItem.getProduct().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Ничего не найдено с id: " + updCartItem.getProduct().getId(), exception.getMessage());
    }

    @Test
    void update_ProductIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        product1.setId(0);
        updCartItem.setProduct(product1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_ProductIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        product1.setId(-1);
        updCartItem.setProduct(product1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(updCartItem.getProduct().getId())).thenReturn(Optional.of(new Product()));

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(updCartItem.getProduct().getId())).thenReturn(Optional.of(new Product()));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(updCartItem.getProduct().getId())).thenReturn(Optional.of(new Product()));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(cartRepository.findById(updCartItem.getCart().getId())).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(updCartItem.getProduct().getId())).thenReturn(Optional.of(new Product()));
        when(cartItemRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> cartItemService.update(updCartItem, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(cartItemRepository.findById(id)).thenReturn(Optional.of(cartItem));

        cartItemService.delete(id);

        verify(cartItemRepository).deleteById(id);
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
