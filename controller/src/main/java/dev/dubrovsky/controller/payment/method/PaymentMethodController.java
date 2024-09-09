package dev.dubrovsky.controller.payment.method;

import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.service.payment.method.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment-method")
@AllArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaymentMethod paymentMethod) {
        paymentMethodService.create(paymentMethod);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(paymentMethodService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(paymentMethodService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PaymentMethod paymentMethod,
                                    @PathVariable Integer id) {
        paymentMethodService.update(paymentMethod, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        paymentMethodService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
