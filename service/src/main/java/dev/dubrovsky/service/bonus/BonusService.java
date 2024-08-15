package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dao.bonus.BonusDao;
import dev.dubrovsky.dao.loyalty.program.LoyaltyProgramDao;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BonusService implements IBonusService {

    private final BonusDao bonusDao;
    private final LoyaltyProgramDao loyaltyProgramDao;

    @Override
    public Bonus create(Bonus bonus) {
        validateBonus(bonus);
        ValidationUtil.checkEntityPresent(bonus.getProgram().getId(), loyaltyProgramDao);

        return bonusDao.create(bonus);
    }

    @Override
    public Bonus getById(Integer id) {
        ValidationUtil.checkId(id, bonusDao);

        return bonusDao.getById(id);
    }

    @Override
    public List<Bonus> getAll() {
        if (bonusDao.getAll().isEmpty() && bonusDao.getAll() == null) {
            return null;
        } else {
            return bonusDao.getAll();
        }
    }

    @Override
    public Bonus update(Bonus bonus, Integer id) {
        validateBonus(bonus);
        ValidationUtil.checkId(id, bonusDao);
        ValidationUtil.checkEntityPresent(bonus.getProgram().getId(), loyaltyProgramDao);

        bonus.setId(id);
        return bonusDao.update(bonus);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, bonusDao);

        return bonusDao.delete(id);
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
