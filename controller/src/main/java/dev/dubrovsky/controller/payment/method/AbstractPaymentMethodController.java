package dev.dubrovsky.controller.payment.method;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.payment.method.NewPaymentMethodRequest;
import dev.dubrovsky.dto.request.payment.method.UpdatePaymentMethodRequest;
import dev.dubrovsky.dto.response.payment.method.PaymentMethodResponse;
import dev.dubrovsky.service.payment.method.PaymentMethodService;

public abstract class AbstractPaymentMethodController extends AbstractController<PaymentMethodService, PaymentMethodResponse, NewPaymentMethodRequest, UpdatePaymentMethodRequest> {

    public AbstractPaymentMethodController(PaymentMethodService service) {
        super(service);
    }

}
