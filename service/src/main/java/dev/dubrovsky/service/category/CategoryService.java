package dev.dubrovsky.service.category;

import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.model.category.Category;
import dev.dubrovsky.util.validation.ValidationUtil;

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
        ValidationUtil.checkId(id, categoryDao);

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
        ValidationUtil.checkId(id, categoryDao);

        categoryDao.update(category);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, categoryDao);

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

}
