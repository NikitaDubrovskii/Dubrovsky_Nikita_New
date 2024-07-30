package dev.dubrovsky.dao.bonus;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.bonus.Bonus;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class BonusDao extends AbstractDao<Bonus> implements IBonusDao {

    public BonusDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Bonus.class);
    }

}
