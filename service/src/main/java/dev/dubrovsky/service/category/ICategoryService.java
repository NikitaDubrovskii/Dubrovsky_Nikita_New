package dev.dubrovsky.service.category;

import dev.dubrovsky.dto.request.category.NewCategoryRequest;
import dev.dubrovsky.dto.request.category.UpdateCategoryRequest;
import dev.dubrovsky.dto.response.category.CategoryResponse;
import dev.dubrovsky.service.ICommonService;

public interface ICategoryService extends ICommonService<CategoryResponse, NewCategoryRequest, UpdateCategoryRequest> {
}
