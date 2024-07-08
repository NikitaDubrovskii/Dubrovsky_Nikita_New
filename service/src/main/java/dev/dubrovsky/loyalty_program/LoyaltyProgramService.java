package dev.dubrovsky.loyalty_program;

public class LoyaltyProgramService implements ILoyaltyProgramService {

    private final ILoyaltyProgramDao loyaltyProgramDao;

    public LoyaltyProgramService(ILoyaltyProgramDao loyaltyProgramDao) {
        this.loyaltyProgramDao = loyaltyProgramDao;
    }

    @Override
    public void create(LoyaltyProgram entity) {
        loyaltyProgramDao.create(entity);
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
    public void update(LoyaltyProgram entity) {
        loyaltyProgramDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            loyaltyProgramDao.delete(id);
        }
    }

}
