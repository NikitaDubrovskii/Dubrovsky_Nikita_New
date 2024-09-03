package dev.dubrovsky.service.category;

import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.model.category.Category;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryDao categoryDao;

    @Override
    public Category create(Category category) {
        validateCategory(category);

        return categoryDao.create(category);
    }

    @Override
    public Category getById(Integer id) {
        ValidationUtil.checkId(id, categoryDao);

        return categoryDao.getById(id);
    }

    @Override
    public List<Category> getAll() {
        if (categoryDao.getAll().isEmpty() && categoryDao.getAll() == null) {
            return null;
        } else {
            return categoryDao.getAll();
        }
    }

    @Override
    public Category update(Category category, Integer id) {
        validateCategory(category);
        ValidationUtil.checkId(id, categoryDao);

        return categoryDao.update(category);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, categoryDao);

        return categoryDao.delete(id);
    }

    private void validateCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Категория не может отсутствовать");
        }
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Название должно быть");
        }
    }

}
