package dev.dubrovsky.controller.category;

import dev.dubrovsky.dto.request.category.NewCategoryRequest;
import dev.dubrovsky.dto.request.category.UpdateCategoryRequest;
import dev.dubrovsky.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
@Tag(name = "Категории", description = "Взаимодействие с категориями")
public class CategoryController extends AbstractCategoryController {

    public CategoryController(CategoryService categoryService) {
        super(categoryService);
    }

    @Override
    @Operation(summary = "Создание категории (admin)", description = "Создание категории, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewCategoryRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение категории (admin)", description = "Получение категории по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка категорий (admin)", description = "Получение списка категорий, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Получение списка категорий (public)", description = "Получение списка категорий, доступно незарегистрированным пользователям")
    public ResponseEntity<?> getAllPublic() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Получение категории (public)", description = "Получение категории по id, доступно незарегистрированным пользователям")
    public ResponseEntity<?> getByIdPublic(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Обновление категории (admin)", description = "Обновление категории по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id,
                                    UpdateCategoryRequest request,
                                    BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление категории (admin)", description = "Удаление категории по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
