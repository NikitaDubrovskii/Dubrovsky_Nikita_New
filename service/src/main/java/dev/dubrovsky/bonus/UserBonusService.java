package dev.dubrovsky.bonus;

public class UserBonusService implements IUserBonusService {

    private final IUserBonusDao userBonusDao;

    public UserBonusService(IUserBonusDao userBonusDao) {
        this.userBonusDao = userBonusDao;
    }

    @Override
    public void create(UserBonus entity) {
        userBonusDao.create(entity);
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
        if (userId < 1 || bonusId < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            userBonusDao.delete(userId, bonusId);
        }
    }

}
