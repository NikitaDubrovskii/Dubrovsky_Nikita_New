package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoyaltyProgramService implements ILoyaltyProgramService {

    private final LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public LoyaltyProgram create(LoyaltyProgram loyaltyProgram) {
        validateLoyaltyProgram(loyaltyProgram);

        return loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Override
    public LoyaltyProgram getById(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramRepository);

        return loyaltyProgramRepository.findById(id).orElse(null);
    }

    @Override
    public List<LoyaltyProgram> getAll() {
        if (loyaltyProgramRepository.findAll().isEmpty()) {
            return null;
        } else {
            return loyaltyProgramRepository.findAll();
        }
    }

    @Override
    public LoyaltyProgram update(LoyaltyProgram loyaltyProgram, Integer id) {
        validateLoyaltyProgram(loyaltyProgram);
        ValidationUtil.checkId(id, loyaltyProgramRepository);

        loyaltyProgram.setId(id);
        return loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramRepository);
        loyaltyProgramRepository.deleteById(id);

        return "Удалено!";
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
