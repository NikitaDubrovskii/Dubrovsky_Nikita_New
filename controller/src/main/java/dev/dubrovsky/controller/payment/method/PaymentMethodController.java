package dev.dubrovsky.controller.payment.method;

import dev.dubrovsky.dto.request.payment.method.NewPaymentMethodRequest;
import dev.dubrovsky.dto.request.payment.method.UpdatePaymentMethodRequest;
import dev.dubrovsky.service.payment.method.PaymentMethodService;
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
@RequestMapping("api/v1/payment-method")
@AllArgsConstructor
@Tag(name="Способы оплаты", description="Взаимодействие со способами оплаты")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @Operation(summary = "Создание способа оплаты", description = "Создание способа оплаты")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewPaymentMethodRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            paymentMethodService.create(request);
            return new ResponseEntity<>("Создано!", HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение способа оплаты", description = "Получение способа оплаты по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(paymentMethodService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение списка способов оплаты", description = "Получение списка способов оплаты")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(paymentMethodService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Обновление способа оплаты", description = "Обновление способа оплаты по id")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdatePaymentMethodRequest request,
                                    @PathVariable Integer id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            paymentMethodService.update(request, id);
            return new ResponseEntity<>("Обновлено!", HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление способа оплаты", description = "Удаление способа оплаты по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        paymentMethodService.delete(id);
        return new ResponseEntity<>("Удалено!", HttpStatus.OK);
    }

}
