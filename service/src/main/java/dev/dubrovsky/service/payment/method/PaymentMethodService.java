package dev.dubrovsky.service.payment.method;

import dev.dubrovsky.dto.request.payment.method.NewPaymentMethodRequest;
import dev.dubrovsky.dto.request.payment.method.UpdatePaymentMethodRequest;
import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.repository.payment.method.PaymentMethodRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentMethodService implements IPaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod create(NewPaymentMethodRequest request) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod(request.method());

        validatePaymentMethod(paymentMethod);

        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod getById(Integer id) {
        ValidationUtil.checkId(id, paymentMethodRepository);

        return paymentMethodRepository.findById(id).orElse(null);
    }

    @Override
    public List<PaymentMethod> getAll() {
        if (paymentMethodRepository.findAll().isEmpty()) {
            return null;
        } else {
            return paymentMethodRepository.findAll();
        }
    }

    @Override
    public PaymentMethod update(UpdatePaymentMethodRequest request, Integer id) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod(request.method());

        validatePaymentMethod(paymentMethod);
        ValidationUtil.checkId(id, paymentMethodRepository);

        paymentMethod.setId(id);
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, paymentMethodRepository);
        paymentMethodRepository.deleteById(id);

        return "Удалено!";
    }

    private void validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Способ оплаты не может отсутствовать");
        }
    }

}
