package dev.dubrovsky.service.category;

import dev.dubrovsky.model.category.Category;
import dev.dubrovsky.repository.category.CategoryRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        validateCategory(category);

        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Integer id) {
        ValidationUtil.checkId(id, categoryRepository);

        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAll() {
        if (categoryRepository.findAll().isEmpty()) {
            return null;
        } else {
            return categoryRepository.findAll();
        }
    }

    @Override
    public Category update(Category category, Integer id) {
        validateCategory(category);
        ValidationUtil.checkId(id, categoryRepository);

        return categoryRepository.save(category);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, categoryRepository);
        categoryRepository.deleteById(id);

        return "Удалено!";
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
