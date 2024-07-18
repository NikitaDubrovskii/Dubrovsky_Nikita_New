package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dao.bonus.BonusDao;
import dev.dubrovsky.dao.loyalty_program.LoyaltyProgramDao;
import dev.dubrovsky.model.bonus.Bonus;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        checkProgramPresent(bonus.getProgramId());

        bonusDao.create(bonus);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

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
        checkProgramPresent(bonus.getProgramId());
        checkId(id);

        bonus.setId(id);
        bonusDao.update(bonus);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        bonusDao.delete(id);
    }

    private void validateBonus(Bonus bonus) {
        if (bonus == null) {
            throw new IllegalArgumentException("Бонус не может отсутствовать");
        }
        if (bonus.getName() == null || bonus.getName().isEmpty()) {
            throw new IllegalArgumentException("Название должно быть");
        }
        if (bonus.getPoints() == null) {
            throw new IllegalArgumentException("Количество очков должно быть");
        }
    }

    private void checkProgramPresent(Integer programId) {
        if (programId > 0) {
            Optional
                    .ofNullable(loyaltyProgramDao.getById(programId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + programId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional.
                    ofNullable(bonusDao.getById(id)).
                    orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
