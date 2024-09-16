package dev.dubrovsky.controller.cart;

import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.service.cart.CartItemService;
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
@RequestMapping("api/v1/cart-item")
@AllArgsConstructor
@Tag(name="Товары в корзине", description="Взаимодействие с товарами в корзине")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    @Operation(summary = "Создание товара в корзине", description = "Создание товара в корзине")
    public ResponseEntity<?> create(@RequestBody @Valid NewCartItemRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            cartItemService.create(request);
            return new ResponseEntity<>("Создано!", HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение товара в корзине", description = "Получение товара в корзине по id товара")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(cartItemService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение списка товаров в корзинах", description = "Получение списка товаров в корзинах")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(cartItemService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Обновление товаров в корзине", description = "Обновление товаров в корзине по id товара")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCartItemRequest request,
                                    @PathVariable Integer id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            cartItemService.update(request, id);
            return new ResponseEntity<>("Обновлено!", HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление товара из корзины", description = "Удаление товара из корзины по id товара")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cartItemService.delete(id);
        return new ResponseEntity<>("Удалено!", HttpStatus.OK);
    }

}
