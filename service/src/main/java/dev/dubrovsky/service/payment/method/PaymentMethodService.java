package dev.dubrovsky.service.payment.method;

import dev.dubrovsky.dao.payment.method.PaymentMethodDao;
import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.util.validation.ValidationUtil;

public class PaymentMethodService implements IPaymentMethodService {

    private final PaymentMethodDao paymentMethodDao;

    public PaymentMethodService(PaymentMethodDao paymentMethodDao) {
        this.paymentMethodDao = paymentMethodDao;
    }

    @Override
    public void create(PaymentMethod paymentMethod) {
        validatePaymentMethod(paymentMethod);

        paymentMethodDao.create(paymentMethod);
    }

    @Override
    public void getById(Integer id) {
        ValidationUtil.checkId(id, paymentMethodDao);

        System.out.println(paymentMethodDao.getById(id));
    }

    @Override
    public void getAll() {
        if (paymentMethodDao.getAll().isEmpty() && paymentMethodDao.getAll() == null) {
            System.out.println("Таблица способов оплаты пустая");
        } else {
            paymentMethodDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(PaymentMethod paymentMethod, Integer id) {
        validatePaymentMethod(paymentMethod);
        ValidationUtil.checkId(id, paymentMethodDao);

        paymentMethod.setId(id);
        paymentMethodDao.update(paymentMethod);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, paymentMethodDao);

        paymentMethodDao.delete(id);
    }

    private void validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Способ оплаты не может отсутствовать");
        }
    }

}
