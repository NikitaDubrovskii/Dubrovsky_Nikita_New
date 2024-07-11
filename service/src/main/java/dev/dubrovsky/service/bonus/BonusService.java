package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dao.bonus.IBonusDao;
import dev.dubrovsky.model.bonus.Bonus;

public class BonusService implements IBonusService {

    private final IBonusDao bonusDao;

    public BonusService(IBonusDao bonusDao) {
        this.bonusDao = bonusDao;
    }

    @Override
    public void create(Bonus entity) {
        bonusDao.create(entity);
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
    public void update(Bonus entity) {
        bonusDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            bonusDao.delete(id);
        }
    }

}
