package dev.dubrovsky.user;

public class UserService implements IUserService {

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User entity) {
        userDao.create(entity);
    }

    @Override
    public void getAll() {
        if (userDao.getAll().isEmpty() && userDao.getAll() == null) {
            System.out.println("Таблица пользователей пустая");
        } else {
            userDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            userDao.delete(id);
        }
    }

}
