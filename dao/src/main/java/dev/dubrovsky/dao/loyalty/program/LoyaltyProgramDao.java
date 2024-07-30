package dev.dubrovsky.dao.loyalty.program;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoyaltyProgramDao extends AbstractDao<LoyaltyProgram> implements ILoyaltyProgramDao {

    public LoyaltyProgramDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, LoyaltyProgram.class);
    }

}
