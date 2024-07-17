package dev.dubrovsky.service.loyalty_program;

import dev.dubrovsky.dao.loyalty_program.ILoyaltyProgramDao;
import dev.dubrovsky.dao.loyalty_program.IUserLoyaltyProgramDao;
import dev.dubrovsky.dao.user.IUserDao;
import dev.dubrovsky.model.loyalty_program.UserLoyaltyProgram;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UserLoyaltyProgramService implements IUserLoyaltyProgramService {

    private final IUserLoyaltyProgramDao userLoyaltyProgramDao;
    private final IUserDao userDao;
    private final ILoyaltyProgramDao loyaltyProgramDao;

    public UserLoyaltyProgramService(IUserLoyaltyProgramDao userLoyaltyProgramDao, IUserDao userDao, ILoyaltyProgramDao loyaltyProgramDao) {
        this.userLoyaltyProgramDao = userLoyaltyProgramDao;
        this.userDao = userDao;
        this.loyaltyProgramDao = loyaltyProgramDao;
    }

    @Override
    public void create(UserLoyaltyProgram userLoyaltyProgram) {
        validateUserLoyaltyProgram(userLoyaltyProgram);
        checkId(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId(), userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId());

        userLoyaltyProgramDao.create(userLoyaltyProgram);
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
        checkId(userId, programId);

        userLoyaltyProgramDao.delete(userId, programId);
    }

    private void validateUserLoyaltyProgram(UserLoyaltyProgram userLoyaltyProgram) {
        if (userLoyaltyProgram == null) {
            throw new IllegalArgumentException("Программа лояльности пользователя не может быть пустой");
        }
    }

    private void checkId(Integer userId, Integer programId) {
        if (userId > 0) {
            Optional
                    .ofNullable(userDao.getById(userId))
                    .orElseThrow(() -> new NoSuchElementException("Пользователь не найден с id: " + userId));
        }
        if (programId > 0) {
            Optional
                    .ofNullable(loyaltyProgramDao.getById(programId))
                    .orElseThrow(() -> new NoSuchElementException("Программа лояльности не найдена с id: " + programId));
        }
        if (userId < 1 || programId < 1) {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
