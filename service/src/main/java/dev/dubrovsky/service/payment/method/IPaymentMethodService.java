package dev.dubrovsky.service.payment.method;

import dev.dubrovsky.dto.request.payment.method.NewPaymentMethodRequest;
import dev.dubrovsky.dto.request.payment.method.UpdatePaymentMethodRequest;
import dev.dubrovsky.dto.response.payment.method.PaymentMethodResponse;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.payment.method.PaymentMethod;

public interface IPaymentMethodService extends ICommonService<PaymentMethodResponse, NewPaymentMethodRequest, UpdatePaymentMethodRequest> {
}
