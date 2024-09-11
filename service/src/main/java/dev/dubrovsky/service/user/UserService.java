package dev.dubrovsky.service.user;

import dev.dubrovsky.dto.request.user.NewUserRequest;
import dev.dubrovsky.dto.request.user.UpdateUserRequest;
import dev.dubrovsky.dto.response.user.UserResponse;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.encoder.SimplePasswordEncoder;
import dev.dubrovsky.util.validation.ValidationUtil;
import jakarta.persistence.NonUniqueResultException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public void create(NewUserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(SimplePasswordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setCreatedAt(LocalDateTime.now());

        validateUser(user);
        checkUniqueUsername(user.getUsername());
        checkUniqueEmail(user.getEmail());

        user.setPassword(SimplePasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserResponse getById(Integer id) {
        ValidationUtil.checkId(id, userRepository);

        User user = userRepository.findById(id).orElse(null);
        return user.mapToResponse();
    }

    @Override
    public List<UserResponse> getAll() {
        if (userRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<UserResponse> responses = new ArrayList<>();
            List<User> all = userRepository.findAll();

            all.forEach(user -> responses.add(user.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateUserRequest request, Integer id) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());

        validateUser(user);
        ValidationUtil.checkId(id, userRepository);

        User existingUser = userRepository.findById(id).orElse(null);
        if (!existingUser.getUsername().equals(user.getUsername())) {
            checkUniqueUsername(user.getUsername());
        }
        if (!existingUser.getEmail().equals(user.getEmail())) {
            checkUniqueEmail(user.getEmail());
        }

        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, userRepository);
        userRepository.deleteById(id);
    }

    @Override
    public void loginUser(String usernameOrEmail, String password) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
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
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Неверная почта, пользователь не найден");
        }
        String tempPassword = generateTemporaryPassword();
        user.setPassword(SimplePasswordEncoder.encode(tempPassword));
        userRepository.save(user);
        System.out.println("Отправка временного пароля на почту " + email + ": " + tempPassword);
    }

    @Override
    public void resetPassword(String usernameOrEmail, String oldPassword, String newPassword) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user == null) {
            throw new IllegalArgumentException("Неверно имя пользователя или почта");
        }
        if (!SimplePasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }
        user.setPassword(SimplePasswordEncoder.encode(newPassword));
        userRepository.save(user);
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
            User user = userRepository.findByUsername(username);
            if (user != null) {
                throw new IllegalArgumentException("Пользователь уже существует с именем: " + username);
            }
        } catch (NonUniqueResultException e) {
            throw new IllegalArgumentException("Имя не уникально: " + username);
        }
    }

    private void checkUniqueEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);
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
