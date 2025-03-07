package dev.dubrovsky.service.payment.method;

import dev.dubrovsky.dto.request.payment.method.NewPaymentMethodRequest;
import dev.dubrovsky.dto.request.payment.method.UpdatePaymentMethodRequest;
import dev.dubrovsky.dto.response.payment.method.PaymentMethodResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.repository.payment.method.PaymentMethodRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentMethodService implements IPaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    private final ModelMapper mapper;

    @Override
    public void create(NewPaymentMethodRequest request) {
        PaymentMethod paymentMethod = mapper
                .typeMap(NewPaymentMethodRequest.class, PaymentMethod.class)
                .addMappings(mapper -> mapper.skip(PaymentMethod::setId))
                .map(request);

        paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethodResponse getById(Integer id) {
        ValidationUtil.checkId(id, paymentMethodRepository);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return paymentMethod.mapToResponse();
    }

    @Override
    public List<PaymentMethodResponse> getAll() {
        if (paymentMethodRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<PaymentMethodResponse> responses = new ArrayList<>();
            List<PaymentMethod> all = paymentMethodRepository.findAll();

            all.forEach(paymentMethod -> responses.add(paymentMethod.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdatePaymentMethodRequest request, Integer id) {
        ValidationUtil.checkId(id, paymentMethodRepository);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.getMethod() != null && !request.getMethod().isEmpty()) {
            paymentMethod.setMethod(request.getMethod());
        }
        paymentMethod.setId(id);

        paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, paymentMethodRepository);
        paymentMethodRepository.deleteById(id);
    }

}
