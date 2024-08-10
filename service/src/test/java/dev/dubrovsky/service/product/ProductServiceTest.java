package dev.dubrovsky.service.product;

import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.category.Category;
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
class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Product updProduct;
    private Category category;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productDao, categoryDao);

        category = new Category();
        category.setId(1);

        product = new Product("test", "test", 10.00F, category);
        product.setId(1);
        updProduct = new Product("upd", "upd", 55.00F, category);
    }

    @Test
    void create_Success() {
        when(categoryDao.getById(product.getCategory().getId())).thenReturn(new Category());

        productService.create(product);

        verify(productDao).create(product);
    }

    @Test
    void create_ProductIsNull_ThrowIllegalArgumentException() {
        product = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        assertEquals("Товар не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_NameIsNull_ThrowIllegalArgumentException() {
        product.setName(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        assertEquals("Название не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_NameIsEmpty_ThrowIllegalArgumentException() {
        product.setName(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        assertEquals("Название не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_PriceIsNull_ThrowIllegalArgumentException() {
        product.setPrice(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        assertEquals("Цена не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_PriceIsZero_ThrowIllegalArgumentException() {
        product.setPrice(0F);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        assertEquals("Цена не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_PriceIsNegative_ThrowIllegalArgumentException() {
        product.setPrice(-44F);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        assertEquals("Цена не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_CategoryIdIsNull_ThrowNullPointerException() {
        product.setCategory(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> productService.create(product));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.category.Category.getId()\" because the return value of \"dev.dubrovsky.model.product.Product.getCategory()\" is null", exception.getMessage());
    }

    @Test
    void create_CategoryNotFound_ThrowNoSuchElementException() {
        when(categoryDao.getById(product.getCategory().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> productService.create(product));
        assertEquals("Ничего не найдено с id: " + product.getCategory().getId(), exception.getMessage());
    }

    @Test
    void create_CategoryIdIsNegative_ThrowIllegalArgumentException() {
        category.setId(-1);
        product.setCategory(category);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_LoyaltyProgramIdIsZero_ThrowIllegalArgumentException() {
        category.setId(0);
        product.setCategory(category);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.create(product));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(productDao.getById(id)).thenReturn(product);

        productService.getById(id);

        verify(productDao, times(2)).getById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> productService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(productDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> productService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        Product product2 = new Product("upd", "upd", 22F, category);
        product2.setId(2);
        List<Product> productList = List.of(
                product,
                product2
        );

        when(productDao.getAll()).thenReturn(productList);

        productService.getAll();

        verify(productDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(productDao.getAll()).thenReturn(Collections.emptyList());

        productService.getAll();

        verify(productDao, times(3)).getAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(categoryDao.getById(updProduct.getCategory().getId())).thenReturn(new Category());
        when(productDao.getById(id)).thenReturn(product);

        productService.update(updProduct, id);

        verify(productDao).update(updProduct);
    }

    @Test
    void update_ProductIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updProduct = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Товар не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_NameIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updProduct.setName(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Название не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_NameIsEmpty_ThrowIllegalArgumentException() {
        Integer id = 1;
        updProduct.setName(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Название не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_PriceIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updProduct.setPrice(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Цена не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_PriceIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        updProduct.setPrice(0F);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Цена не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_PriceIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        updProduct.setPrice(-44F);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Цена не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;
        when(categoryDao.getById(updProduct.getCategory().getId())).thenReturn(new Category());

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> productService.update(updProduct, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;
        when(categoryDao.getById(updProduct.getCategory().getId())).thenReturn(new Category());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;
        when(categoryDao.getById(updProduct.getCategory().getId())).thenReturn(new Category());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->  productService.update(updProduct, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(categoryDao.getById(updProduct.getCategory().getId())).thenReturn(new Category());
        when(productDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> productService.update(updProduct, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void update_CategoryIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        updProduct.setCategory(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> productService.update(updProduct, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.category.Category.getId()\" because the return value of \"dev.dubrovsky.model.product.Product.getCategory()\" is null", exception.getMessage());
    }

    @Test
    void update_CategoryIdIsZero_ThrowNullPointerException() {
        Integer id = 1;
        category.setId(0);
        updProduct.setCategory(category);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_CategoryNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(categoryDao.getById(updProduct.getCategory().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> productService.update(updProduct, id));
        assertEquals("Ничего не найдено с id: " + updProduct.getCategory().getId(), exception.getMessage());
    }

    @Test
    void update_CategoryIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        category.setId(-1);
        updProduct.setCategory(category);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> productService.update(updProduct, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());

    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(productDao.getById(id)).thenReturn(product);

        productService.delete(id);

        verify(productDao).delete(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> productService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> productService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(productDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> productService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

}
