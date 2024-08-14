package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dao.bonus.BonusDao;
import dev.dubrovsky.dao.bonus.UserBonusDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.bonus.UserBonus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserBonusService implements IUserBonusService {

    private final UserBonusDao userBonusDao;
    private final UserDao userDao;
    private final BonusDao bonusDao;

    public UserBonusService(UserBonusDao userBonusDao, UserDao userDao, BonusDao bonusDao) {
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
