package dev.dubrovsky.loyalty_program;

public class UserLoyaltyProgramService implements IUserLoyaltyProgramService {

    private final IUserLoyaltyProgramDao userLoyaltyProgramDao;

    public UserLoyaltyProgramService(IUserLoyaltyProgramDao userLoyaltyProgramDao) {
        this.userLoyaltyProgramDao = userLoyaltyProgramDao;
    }

    @Override
    public void create(UserLoyaltyProgram entity) {
        userLoyaltyProgramDao.create(entity);
    }

    @Override
    public void getAll() {
        if (userLoyaltyProgramDao.getAll().isEmpty() && userLoyaltyProgramDao.getAll() == null) {
            System.out.println("Таблица программ лояльности пользователя пустая");
        } else {
            userLoyaltyProgramDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void delete(Integer userId, Integer programId) {
        if (userId < 1 || programId < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            userLoyaltyProgramDao.delete(userId, programId);
        }
    }

}
