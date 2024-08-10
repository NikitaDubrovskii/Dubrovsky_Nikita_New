package dev.dubrovsky.service.user;

import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.util.encoder.SimplePasswordEncoder;
import dev.dubrovsky.util.validation.ValidationUtil;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        validateUser(user);
        checkUniqueUsername(user.getUsername());
        checkUniqueEmail(user.getEmail());

        user.setPassword(SimplePasswordEncoder.encode(user.getPassword()));
        userDao.create(user);
    }

    @Override
    public User getById(Integer id) {
        ValidationUtil.checkId(id, userDao);

        return userDao.getById(id);
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

    @Override
    public void loginUser(String usernameOrEmail, String password) {
        User user = userDao.findByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            throw new IllegalArgumentException("Неверно имя пользователя или почта");
        }
        if (!SimplePasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }

        System.out.println("Вход выполнен");
    }

    @Override
    public void recoverPassword(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Неверная почта, пользователь не найден");
        }
        String tempPassword = generateTemporaryPassword();
        user.setPassword(SimplePasswordEncoder.encode(tempPassword));
        userDao.update(user);
        System.out.println("Отправка временного пароля на почту " + email + ": " + tempPassword);
    }

    @Override
    public void resetPassword(String usernameOrEmail, String oldPassword, String newPassword) {
        User user = userDao.findByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            throw new IllegalArgumentException("Неверно имя пользователя или почта");
        }
        if (!SimplePasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }
        user.setPassword(SimplePasswordEncoder.encode(newPassword));
        userDao.update(user);
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

    private String generateTemporaryPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

}
