package dev.dubrovsky.controller.product;

import dev.dubrovsky.dto.request.product.NewProductRequest;
import dev.dubrovsky.dto.request.product.UpdateProductRequest;
import dev.dubrovsky.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
@Tag(name = "Товары", description = "Взаимодействие с товарами")
public class ProductController extends AbstractProductController {

    public ProductController(ProductService productService) {
        super(productService);
    }

    @Override
    @Operation(summary = "Создание товара (admin)", description = "Создание товара, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewProductRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение товара (admin)", description = "Получение товара по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка товаров (admin)", description = "Получение списка товаров, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Получение списка товаров (public)", description = "Получение списка товаров, доступно незарегистрированным пользователям")
    public ResponseEntity<?> getAllPublic() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Получение товара (public)", description = "Получение товара по id, доступно незарегистрированным пользователям")
    public ResponseEntity<?> getByIdPublic(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Обновление товара (admin)", description = "Обновление товара по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id,
                                    UpdateProductRequest request,
                                    BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление товара (admin)", description = "Удаление товара по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
