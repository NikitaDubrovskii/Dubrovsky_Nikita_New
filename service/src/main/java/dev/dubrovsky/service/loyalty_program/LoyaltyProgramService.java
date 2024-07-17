package dev.dubrovsky.service.loyalty_program;

import dev.dubrovsky.dao.loyalty_program.ILoyaltyProgramDao;
import dev.dubrovsky.model.loyalty_program.LoyaltyProgram;

import java.util.NoSuchElementException;
import java.util.Optional;

public class LoyaltyProgramService implements ILoyaltyProgramService {

    private final ILoyaltyProgramDao loyaltyProgramDao;

    public LoyaltyProgramService(ILoyaltyProgramDao loyaltyProgramDao) {
        this.loyaltyProgramDao = loyaltyProgramDao;
    }

    @Override
    public void create(LoyaltyProgram loyaltyProgram) {
        validateLoyaltyProgram(loyaltyProgram);

        loyaltyProgramDao.create(loyaltyProgram);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

        System.out.println(loyaltyProgramDao.getById(id));
    }

    @Override
    public void getAll() {
        if (loyaltyProgramDao.getAll().isEmpty() && loyaltyProgramDao.getAll() == null) {
            System.out.println("Таблица программ лояльности пустая");
        } else {
            loyaltyProgramDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(LoyaltyProgram loyaltyProgram, Integer id) {
        validateLoyaltyProgram(loyaltyProgram);
        checkId(id);

        loyaltyProgram.setId(id);
        loyaltyProgramDao.update(loyaltyProgram);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        loyaltyProgramDao.delete(id);
    }

    private void validateLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        if (loyaltyProgram == null) {
            throw new IllegalArgumentException("Программа лояльности не может отсутствовать");
        }
        if (loyaltyProgram.getName() == null || loyaltyProgram.getName().isEmpty()) {
            throw new IllegalArgumentException("Название должно быть");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(loyaltyProgramDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
