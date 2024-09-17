package dev.dubrovsky.controller.order;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.order.NewOrderItemRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderItemRequest;
import dev.dubrovsky.service.order.OrderItemService;
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
@RequestMapping("api/v1/order-item")
@AllArgsConstructor
@Tag(name="Товары в заказе", description="Взаимодействие с товарами в заказе")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Operation(summary = "Создание товара в заказе", description = "Создание товара в заказе")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewOrderItemRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            orderItemService.create(request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение товара в заказе", description = "Получение товара в заказе по id товара")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(orderItemService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение списка товаров в заказах", description = "Получение списка товаров в заказах")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(orderItemService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Обновление товара в заказе", description = "Обновление товара в заказе по id товара")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateOrderItemRequest request,
                                    @PathVariable Integer id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            orderItemService.update(request, id);
            return new ResponseEntity<>(ResponseStatus.UPDATED.getDescription(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление товара из заказа", description = "Удаление товара из заказа по id товара")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        orderItemService.delete(id);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
