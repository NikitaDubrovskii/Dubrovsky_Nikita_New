package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dao.loyalty.program.LoyaltyProgramDao;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoyaltyProgramService implements ILoyaltyProgramService {

    private final LoyaltyProgramDao loyaltyProgramDao;

    @Override
    public LoyaltyProgram create(LoyaltyProgram loyaltyProgram) {
        validateLoyaltyProgram(loyaltyProgram);

        return loyaltyProgramDao.create(loyaltyProgram);
    }

    @Override
    public LoyaltyProgram getById(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramDao);

        return loyaltyProgramDao.getById(id);
    }

    @Override
    public List<LoyaltyProgram> getAll() {
        if (loyaltyProgramDao.getAll().isEmpty() && loyaltyProgramDao.getAll() == null) {
            return null;
        } else {
            return loyaltyProgramDao.getAll();
        }
    }

    @Override
    public LoyaltyProgram update(LoyaltyProgram loyaltyProgram, Integer id) {
        validateLoyaltyProgram(loyaltyProgram);
        ValidationUtil.checkId(id, loyaltyProgramDao);

        loyaltyProgram.setId(id);
        return loyaltyProgramDao.update(loyaltyProgram);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramDao);

        return loyaltyProgramDao.delete(id);
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
