package dev.dubrovsky.service.payment.method;

import dev.dubrovsky.dto.request.payment.method.NewPaymentMethodRequest;
import dev.dubrovsky.dto.request.payment.method.UpdatePaymentMethodRequest;
import dev.dubrovsky.dto.response.payment.method.PaymentMethodResponse;
import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.repository.payment.method.PaymentMethodRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentMethodService implements IPaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public void create(NewPaymentMethodRequest request) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod(request.method());

        validatePaymentMethod(paymentMethod);

        paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethodResponse getById(Integer id) {
        ValidationUtil.checkId(id, paymentMethodRepository);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElse(null);
        return paymentMethod.mapToResponse();
    }

    @Override
    public List<PaymentMethodResponse> getAll() {
        if (paymentMethodRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<PaymentMethodResponse> responses = new ArrayList<>();
            List<PaymentMethod> all = paymentMethodRepository.findAll();

            all.forEach(paymentMethod -> responses.add(paymentMethod.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdatePaymentMethodRequest request, Integer id) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod(request.method());

        validatePaymentMethod(paymentMethod);
        ValidationUtil.checkId(id, paymentMethodRepository);

        paymentMethod.setId(id);
        paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, paymentMethodRepository);
        paymentMethodRepository.deleteById(id);
    }

    private void validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Способ оплаты не может отсутствовать");
        }
    }

}
