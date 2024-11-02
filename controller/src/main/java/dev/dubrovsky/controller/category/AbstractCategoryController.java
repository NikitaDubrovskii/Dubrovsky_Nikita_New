package dev.dubrovsky.controller.category;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.category.NewCategoryRequest;
import dev.dubrovsky.dto.request.category.UpdateCategoryRequest;
import dev.dubrovsky.dto.response.category.CategoryResponse;
import dev.dubrovsky.service.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractCategoryController extends AbstractController<CategoryService, CategoryResponse, NewCategoryRequest, UpdateCategoryRequest> {

    public AbstractCategoryController(CategoryService service) {
        super(service);
    }

    @GetMapping("/public")
    public abstract ResponseEntity<?> getAllPublic();

    @GetMapping("/public/{id}")
    public abstract ResponseEntity<?> getByIdPublic(@PathVariable Integer id);

}
