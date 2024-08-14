package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dao.bonus.BonusDao;
import dev.dubrovsky.dao.loyalty.program.LoyaltyProgramDao;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.util.validation.ValidationUtil;

public class BonusService implements IBonusService {

    private final BonusDao bonusDao;
    private final LoyaltyProgramDao loyaltyProgramDao;

    public BonusService(BonusDao bonusDao, LoyaltyProgramDao loyaltyProgramDao) {
        this.bonusDao = bonusDao;
        this.loyaltyProgramDao = loyaltyProgramDao;
    }

    @Override
    public void create(Bonus bonus) {
        validateBonus(bonus);
        ValidationUtil.checkEntityPresent(bonus.getProgramId(), loyaltyProgramDao);

        bonusDao.create(bonus);
    }

    @Override
    public void getById(Integer id) {
        ValidationUtil.checkId(id, bonusDao);

        System.out.println(bonusDao.getById(id));
    }

    @Override
    public void getAll() {
        if (bonusDao.getAll().isEmpty() && bonusDao.getAll() == null) {
            System.out.println("Таблица бонусов пустая");
        } else {
            bonusDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(Bonus bonus, Integer id) {
        validateBonus(bonus);
        ValidationUtil.checkId(id, bonusDao);
        ValidationUtil.checkEntityPresent(bonus.getProgramId(), loyaltyProgramDao);

        bonus.setId(id);
        bonusDao.update(bonus);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, bonusDao);

        bonusDao.delete(id);
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
