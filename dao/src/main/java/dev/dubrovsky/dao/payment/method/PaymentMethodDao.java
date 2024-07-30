package dev.dubrovsky.dao.payment.method;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.payment.method.PaymentMethod;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodDao extends AbstractDao<PaymentMethod> implements IPaymentMethodDao {

    public PaymentMethodDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, PaymentMethod.class);
    }

}
