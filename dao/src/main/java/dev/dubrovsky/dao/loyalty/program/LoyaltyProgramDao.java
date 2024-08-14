package dev.dubrovsky.dao.loyalty.program;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;

public class LoyaltyProgramDao extends AbstractDao<LoyaltyProgram> implements ILoyaltyProgramDao {

    public LoyaltyProgramDao(Class<LoyaltyProgram> entityClass) {
        super(entityClass);
    }

}
