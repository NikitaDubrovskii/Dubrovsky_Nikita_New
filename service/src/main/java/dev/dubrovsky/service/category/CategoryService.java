package dev.dubrovsky.service.category;

import dev.dubrovsky.dto.request.category.NewCategoryRequest;
import dev.dubrovsky.dto.request.category.UpdateCategoryRequest;
import dev.dubrovsky.dto.response.category.CategoryResponse;
import dev.dubrovsky.model.category.Category;
import dev.dubrovsky.repository.category.CategoryRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void create(NewCategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());

        validateCategory(category);

        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getById(Integer id) {
        ValidationUtil.checkId(id, categoryRepository);

        Category category = categoryRepository.findById(id).orElse(null);
        return category.mapToResponse();
    }

    @Override
    public List<CategoryResponse> getAll() {
        if (categoryRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<CategoryResponse> responses = new ArrayList<>();
            List<Category> all = categoryRepository.findAll();

            all.forEach(category -> responses.add(category.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateCategoryRequest request, Integer id) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());

        validateCategory(category);
        ValidationUtil.checkId(id, categoryRepository);

        categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, categoryRepository);
        categoryRepository.deleteById(id);
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
