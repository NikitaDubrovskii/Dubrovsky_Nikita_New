package dev.dubrovsky.controller.order;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.service.order.OrderService;
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
@RequestMapping("api/v1/order")
@AllArgsConstructor
@Tag(name="Заказы", description="Взаимодействие с заказами")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Создание заказа", description = "Создание заказа")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewOrderRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            orderService.create(request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение заказа", description = "Получение заказа по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение списка заказов", description = "Получение списка заказов")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Обновление заказа", description = "Обновление заказа по id")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateOrderRequest request,
                                    @PathVariable Integer id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            orderService.update(request, id);
            return new ResponseEntity<>(ResponseStatus.UPDATED.getDescription(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление заказа", description = "Удаление заказа по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        orderService.delete(id);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
