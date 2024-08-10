package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dao.loyalty.program.LoyaltyProgramDao;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.util.validation.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyProgramService implements ILoyaltyProgramService {

    private final LoyaltyProgramDao loyaltyProgramDao;

    public LoyaltyProgramService(LoyaltyProgramDao loyaltyProgramDao) {
        this.loyaltyProgramDao = loyaltyProgramDao;
    }

    @Override
    public void create(LoyaltyProgram loyaltyProgram) {
        validateLoyaltyProgram(loyaltyProgram);

        loyaltyProgramDao.create(loyaltyProgram);
    }

    @Override
    public LoyaltyProgram getById(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramDao);

        return loyaltyProgramDao.getById(id);
    }

    @Override
    public void getAll() {
        if (loyaltyProgramDao.getAll().isEmpty() && loyaltyProgramDao.getAll() == null) {
            System.out.println("Таблица программ лояльности пустая");
        } else {
            loyaltyProgramDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(LoyaltyProgram loyaltyProgram, Integer id) {
        validateLoyaltyProgram(loyaltyProgram);
        ValidationUtil.checkId(id, loyaltyProgramDao);

        loyaltyProgram.setId(id);
        loyaltyProgramDao.update(loyaltyProgram);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramDao);

        loyaltyProgramDao.delete(id);
    }

    private void validateLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        if (loyaltyProgram == null) {
            throw new IllegalArgumentException("Программа лояльности не может отсутствовать");
        }
        if (loyaltyProgram.getName() == null || loyaltyProgram.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Название должно быть");
        }
    }

}
