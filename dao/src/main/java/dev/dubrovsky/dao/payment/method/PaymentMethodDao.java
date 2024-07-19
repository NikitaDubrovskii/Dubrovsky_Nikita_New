package dev.dubrovsky.dao.payment.method;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.payment.method.PaymentMethod;

public class PaymentMethodDao extends AbstractDao<PaymentMethod> implements IPaymentMethodDao {

    public PaymentMethodDao(Class<PaymentMethod> entityClass) {
        super(entityClass);
    }

}
