package dev.dubrovsky.controller.category;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.category.NewCategoryRequest;
import dev.dubrovsky.dto.request.category.UpdateCategoryRequest;
import dev.dubrovsky.dto.response.category.CategoryResponse;
import dev.dubrovsky.service.category.CategoryService;

public abstract class AbstractCategoryController extends AbstractController<CategoryService, CategoryResponse, NewCategoryRequest, UpdateCategoryRequest> {

    public AbstractCategoryController(CategoryService service) {
        super(service);
    }

}
