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
    @Operation(summary = "Создание товара", description = "Создание товара")
    public ResponseEntity<?> create(NewProductRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение товара", description = "Получение товара по id")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка товаров", description = "Получение списка товаров")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление товара", description = "Обновление товара по id")
    public ResponseEntity<?> update(UpdateProductRequest request,
                                    Integer id,
                                    BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление товара", description = "Удаление товара по id")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
