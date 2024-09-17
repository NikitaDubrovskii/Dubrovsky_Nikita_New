package dev.dubrovsky.controller.product;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.product.NewProductRequest;
import dev.dubrovsky.dto.request.product.UpdateProductRequest;
import dev.dubrovsky.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
@Tag(name="Товары", description="Взаимодействие с товарами")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Создание товара", description = "Создание товара")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewProductRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            productService.create(request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение товара", description = "Получение товара по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение списка товаров", description = "Получение списка товаров")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Обновление товара", description = "Обновление товара по id")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateProductRequest request,
                                    @PathVariable Integer id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            productService.update(request, id);
            return new ResponseEntity<>(ResponseStatus.UPDATED.getDescription(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление товара", description = "Удаление товара по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productService.delete(id);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
