package dev.dubrovsky.service.payment_method;

import dev.dubrovsky.dao.payment_method.PaymentMethodDao;
import dev.dubrovsky.model.payment_method.PaymentMethod;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        checkId(id);

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
        checkId(id);

        paymentMethod.setId(id);
        paymentMethodDao.update(paymentMethod);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        paymentMethodDao.delete(id);
    }

    private void validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Способ оплаты не может отсутствовать");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(paymentMethodDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
