package dev.dubrovsky.service.bonus;

import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.repository.bonus.BonusRepository;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BonusService implements IBonusService {

    private final BonusRepository bonusRepository;
    private final LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public Bonus create(Bonus bonus) {
        validateBonus(bonus);
        ValidationUtil.checkEntityPresent(bonus.getProgram().getId(), loyaltyProgramRepository);

        return bonusRepository.save(bonus);
    }

    @Override
    public Bonus getById(Integer id) {
        ValidationUtil.checkId(id, bonusRepository);

        return bonusRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bonus> getAll() {
        if (bonusRepository.findAll().isEmpty()) {
            return null;
        } else {
            return bonusRepository.findAll();
        }
    }

    @Override
    public Bonus update(Bonus bonus, Integer id) {
        validateBonus(bonus);
        ValidationUtil.checkId(id, bonusRepository);
        ValidationUtil.checkEntityPresent(bonus.getProgram().getId(), loyaltyProgramRepository);

        bonus.setId(id);
        return bonusRepository.save(bonus);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, bonusRepository);

        bonusRepository.deleteById(id);
        return "Удалено!";
    }

    private void validateBonus(Bonus bonus) {
        if (bonus == null) {
            throw new IllegalArgumentException("Бонус не может отсутствовать");
        }
        if (bonus.getName() == null || bonus.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Название должно быть");
        }
        if (bonus.getPoints() == null) {
            throw new IllegalArgumentException("Количество очков должно быть");
        }
    }

}
