package dev.dubrovsky.service.payment.method;

import dev.dubrovsky.dao.payment.method.PaymentMethodDao;
import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.util.validation.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService implements IPaymentMethodService {

    private final PaymentMethodDao paymentMethodDao;

    public PaymentMethodService(PaymentMethodDao paymentMethodDao) {
        this.paymentMethodDao = paymentMethodDao;
    }

    @Override
    public PaymentMethod create(PaymentMethod paymentMethod) {
        validatePaymentMethod(paymentMethod);

        return paymentMethodDao.create(paymentMethod);
    }

    @Override
    public PaymentMethod getById(Integer id) {
        ValidationUtil.checkId(id, paymentMethodDao);

        return paymentMethodDao.getById(id);
    }

    @Override
    public List<PaymentMethod> getAll() {
        if (paymentMethodDao.getAll().isEmpty() && paymentMethodDao.getAll() == null) {
            return null;
        } else {
            return paymentMethodDao.getAll();
        }
    }

    @Override
    public PaymentMethod update(PaymentMethod paymentMethod, Integer id) {
        validatePaymentMethod(paymentMethod);
        ValidationUtil.checkId(id, paymentMethodDao);

        paymentMethod.setId(id);
        return paymentMethodDao.update(paymentMethod);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, paymentMethodDao);

        return paymentMethodDao.delete(id);
    }

    private void validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Способ оплаты не может отсутствовать");
        }
    }

}
