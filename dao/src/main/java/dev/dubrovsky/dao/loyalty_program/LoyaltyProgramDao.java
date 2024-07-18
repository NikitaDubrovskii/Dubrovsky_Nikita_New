package dev.dubrovsky.dao.loyalty_program;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.loyalty_program.LoyaltyProgram;

public class LoyaltyProgramDao extends AbstractDao<LoyaltyProgram> implements ILoyaltyProgramDao {

    public LoyaltyProgramDao(Class<LoyaltyProgram> entityClass) {
        super(entityClass);
    }

}
