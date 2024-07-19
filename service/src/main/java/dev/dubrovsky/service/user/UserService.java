package dev.dubrovsky.service.user;

import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.util.validation.ValidationUtil;
import jakarta.persistence.NonUniqueResultException;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UserService implements IUserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        validateUser(user);
        checkUniqueEmail(user.getEmail());
        checkUniqueUsername(user.getUsername());

        userDao.create(user);
    }

    @Override
    public void getById(Integer id) {
        ValidationUtil.checkId(id, userDao);

        System.out.println(userDao.getById(id));
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
    public void update(User user, Integer id) {
        validateUser(user);
        ValidationUtil.checkId(id, userDao);

        User existingUser = userDao.getById(id);
        if (!existingUser.getUsername().equals(user.getUsername())) {
            checkUniqueUsername(user.getUsername());
        }
        if (!existingUser.getEmail().equals(user.getEmail())) {
            checkUniqueEmail(user.getEmail());
        }

        user.setId(id);
        userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, userDao);

        userDao.delete(id);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Пользователь не может отсутствовать");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Почта не может отсутствовать");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Пароль не может отсутствовать");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может отсутствовать");
        }
    }

    private void checkUniqueUsername(String username) {
        try {
            User user = userDao.findByUsername(username);
            if (user != null) {
                throw new IllegalArgumentException("Пользователь уже существует с именем: " + username);
            }
        } catch (NonUniqueResultException e) {
            throw new IllegalArgumentException("Имя не уникально: " + username);
        }
    }

    private void checkUniqueEmail(String email) {
        try {
            User user = userDao.findByEmail(email);
            if (user != null) {
                throw new IllegalArgumentException("Пользователь уже существует с почтой: " + email);
            }
        } catch (NonUniqueResultException e) {
            throw new IllegalArgumentException("Почта не уникальна: " + email);
        }
    }

}
