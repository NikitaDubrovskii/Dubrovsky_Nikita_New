package dev.dubrovsky.service.category;

import dev.dubrovsky.dto.request.category.NewCategoryRequest;
import dev.dubrovsky.dto.request.category.UpdateCategoryRequest;
import dev.dubrovsky.dto.response.category.CategoryResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.category.Category;
import dev.dubrovsky.repository.category.CategoryRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper mapper;

    @Override
    public void create(NewCategoryRequest request) {
        Category category = mapper
                .typeMap(NewCategoryRequest.class, Category.class)
                .addMappings(mapper -> mapper.skip(Category::setId))
                .map(request);
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            category.setDescription(request.getDescription());
        }

        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getById(Integer id) {
        ValidationUtil.checkId(id, categoryRepository);

        Category category = categoryRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return category.mapToResponse();
    }

    @Override
    public List<CategoryResponse> getAll() {
        if (categoryRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<CategoryResponse> responses = new ArrayList<>();
            List<Category> all = categoryRepository.findAll();

            all.forEach(category -> responses.add(category.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateCategoryRequest request, Integer id) {
        ValidationUtil.checkId(id, categoryRepository);

        Category category = categoryRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.getName() != null && !request.getName().isEmpty()) {
            category.setName(request.getName());
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            category.setDescription(request.getDescription());
        }
        category.setId(id);

        categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, categoryRepository);
        categoryRepository.deleteById(id);
    }

}
