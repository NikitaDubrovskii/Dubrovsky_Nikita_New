package dev.dubrovsky.payment_method;

public class PaymentMethodService implements IPaymentMethodService {

    private final IPaymentMethodDao paymentMethodDao;

    public PaymentMethodService(IPaymentMethodDao paymentMethodDao) {
        this.paymentMethodDao = paymentMethodDao;
    }

    @Override
    public void create(PaymentMethod entity) {
        paymentMethodDao.create(entity);
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
    public void update(PaymentMethod entity) {
        paymentMethodDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            paymentMethodDao.delete(id);
        }
    }

}
