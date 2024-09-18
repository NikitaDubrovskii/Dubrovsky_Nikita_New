package dev.dubrovsky.controller.payment.method;

import dev.dubrovsky.dto.request.payment.method.NewPaymentMethodRequest;
import dev.dubrovsky.dto.request.payment.method.UpdatePaymentMethodRequest;
import dev.dubrovsky.service.payment.method.PaymentMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment-method")
@Tag(name = "Способы оплаты", description = "Взаимодействие со способами оплаты")
public class PaymentMethodController extends AbstractPaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        super(paymentMethodService);
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    @Operation(summary = "Создание способа оплаты", description = "Создание способа оплаты")
    public ResponseEntity<?> create(NewPaymentMethodRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение способа оплаты", description = "Получение способа оплаты по id")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка способов оплаты", description = "Получение списка способов оплаты")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление способа оплаты", description = "Обновление способа оплаты по id")
    public ResponseEntity<?> update(UpdatePaymentMethodRequest request,
                                    Integer id,
                                    BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление способа оплаты", description = "Удаление способа оплаты по id")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
