package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dao.bonus.IBonusDao;
import dev.dubrovsky.dao.bonus.IUserBonusDao;
import dev.dubrovsky.dao.user.IUserDao;
import dev.dubrovsky.model.bonus.UserBonus;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UserBonusService implements IUserBonusService {

    private final IUserBonusDao userBonusDao;
    private final IUserDao userDao;
    private final IBonusDao bonusDao;

    public UserBonusService(IUserBonusDao userBonusDao, IUserDao userDao, IBonusDao bonusDao) {
        this.userBonusDao = userBonusDao;
        this.userDao = userDao;
        this.bonusDao = bonusDao;
    }

    @Override
    public void create(UserBonus userBonus) {
        validateUserBonus(userBonus);
        checkId(userBonus.getUserBonusId().getUserId(), userBonus.getUserBonusId().getBonusId());

        userBonusDao.create(userBonus);
    }

    @Override
    public void getAll() {
        if (userBonusDao.getAll().isEmpty() && userBonusDao.getAll() == null) {
            System.out.println("Таблица бонусов пользователей пустая");
        } else {
            userBonusDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void delete(Integer userId, Integer bonusId) {
        checkId(userId, bonusId);

        userBonusDao.delete(userId, bonusId);
    }

    private void validateUserBonus(UserBonus userBonus) {
        if (userBonus == null) {
            throw new IllegalArgumentException("Бонус пользователя не может отсутствовать");
        }
    }

    private void checkId(Integer userId, Integer bonusId) {
        if (userId > 0) {
            Optional
                    .ofNullable(userDao.getById(userId))
                    .orElseThrow(() -> new NoSuchElementException("Пользователь не найден с id: " + userId));
        }
        if (bonusId > 0) {
            Optional
                    .ofNullable(bonusDao.getById(bonusId))
                    .orElseThrow(() -> new NoSuchElementException("Бонус не найден с id: " + userId));
        }
        if (userId < 1 || bonusId < 1) {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
