package dev.dubrovsky.service.category;

import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.model.category.Category;
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
class CategoryServiceTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private Category updCategory;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryDao);

        category = new Category("test", "test");
        category.setId(1);
        updCategory = new Category("upd", "upd");
    }

    @Test
    void create_Success() {
        categoryService.create(category);

        verify(categoryDao).create(category);
    }

    @Test
    void create_CategoryIsNull_ThrowIllegalArgumentException() {
        category = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.create(category));
        assertEquals("Категория не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_NameIsNul_ThrowIllegalArgumentException() {
        category.setName(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.create(category));
        assertEquals("Название должно быть", thrown.getMessage());
    }

    @Test
    void create_NameIsEmpty_ThrowIllegalArgumentException() {
        category.setName(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.create(category));
        assertEquals("Название должно быть", thrown.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(categoryDao.getById(id)).thenReturn(category);

        categoryService.getById(id);

        verify(categoryDao, times(2)).getById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> categoryService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(categoryDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> categoryService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        Category category2 = new Category("test", "test");
        category2.setId(2);
        List<Category> categories = List.of(
                category,
                category2
        );

        when(categoryDao.getAll()).thenReturn(categories);

        categoryService.getAll();

        verify(categoryDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(categoryDao.getAll()).thenReturn(Collections.emptyList());

        categoryService.getAll();

        verify(categoryDao, times(3)).getAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(categoryDao.getById(id)).thenReturn(category);

        categoryService.update(updCategory, id);

        verify(categoryDao).update(updCategory);
    }

    @Test
    void update_CategoryIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCategory = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.update(updCategory, id));
        assertEquals("Категория не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_NameIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCategory.setName(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.update(updCategory, id));
        assertEquals("Название должно быть", thrown.getMessage());
    }

    @Test
    void update_NameIsEmpty_ThrowIllegalArgumentException() {
        Integer id = 1;
        updCategory.setName(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.update(updCategory, id));
        assertEquals("Название должно быть", thrown.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> categoryService.update(updCategory, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.update(updCategory, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.update(updCategory, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(categoryDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> categoryService.update(updCategory, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(categoryDao.getById(id)).thenReturn(category);

        categoryService.delete(id);

        verify(categoryDao).delete(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> categoryService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> categoryService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(categoryDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> categoryService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

}
