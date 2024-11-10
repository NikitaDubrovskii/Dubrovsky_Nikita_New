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

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        super(paymentMethodService);
    }

    @Override
    @Operation(summary = "Создание способа оплаты (admin)", description = "Создание способа оплаты, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewPaymentMethodRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение способа оплаты (admin)", description = "Получение способа оплаты по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка способов оплаты (admin)", description = "Получение списка способов оплаты, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление способа оплаты (admin)", description = "Обновление способа оплаты по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id,
                                    UpdatePaymentMethodRequest request,
                                    BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление способа оплаты (admin)", description = "Удаление способа оплаты по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
