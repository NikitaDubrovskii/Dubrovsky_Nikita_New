package dev.dubrovsky.controller.cart;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.service.cart.CartService;
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
@RequestMapping("api/v1/cart")
@AllArgsConstructor
@Tag(name="Корзины", description="Взаимодействие с корзиной")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Создание корзины", description = "Создание корзины")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewCartRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            cartService.create(request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение корзины", description = "Получение корзины по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(cartService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение списка корзин", description = "Получение списка корзин")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(cartService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Обновление корзины", description = "Обновление корзины по id")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCartRequest request,
                                    @PathVariable Integer id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            cartService.update(request, id);
            return new ResponseEntity<>(ResponseStatus.UPDATED.getDescription(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление корзины", description = "Удаление корзины по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cartService.delete(id);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
