package dev.dubrovsky.service.category;

import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.model.category.Category;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CategoryService implements ICategoryService {

    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void create(Category category) {
        validateCategory(category);

        categoryDao.create(category);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

        categoryDao.getById(id);
    }

    @Override
    public void getAll() {
        if (categoryDao.getAll().isEmpty() && categoryDao.getAll() == null) {
            System.out.println("Таблица категорий пустая");
        } else {
            categoryDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(Category category, Integer id) {
        validateCategory(category);
        checkId(id);

        categoryDao.update(category);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        categoryDao.delete(id);
    }

    private void validateCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Категория не может отсутствовать");
        }
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Название должно быть");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(categoryDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
