package dev.dubrovsky.dao.bonus;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.bonus.Bonus;

public class BonusDao extends AbstractDao<Bonus> implements IBonusDao {

    public BonusDao(Class<Bonus> entityClass) {
        super(entityClass);
    }

}
