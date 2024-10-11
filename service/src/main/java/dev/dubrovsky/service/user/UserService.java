package dev.dubrovsky.service.user;

import dev.dubrovsky.dto.SimpleTextResponse;
import dev.dubrovsky.dto.request.user.NewUserRequest;
import dev.dubrovsky.dto.request.user.UpdateUserRequest;
import dev.dubrovsky.dto.request.user.UserLoginRequest;
import dev.dubrovsky.dto.request.user.UserResetPasswordRequest;
import dev.dubrovsky.dto.response.user.UserResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.encoder.SimplePasswordEncoder;
import dev.dubrovsky.util.jwt.JWTTokenUtil;
import dev.dubrovsky.util.validation.ValidationUtil;
import jakarta.persistence.NonUniqueResultException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    private final JWTTokenUtil jwtTokenUtil;

    @Override
    public void create(NewUserRequest request) {
        checkUniqueUsername(request.getUsername());
        checkUniqueEmail(request.getEmail());

        User user = mapper
                .typeMap(NewUserRequest.class, User.class)
                .addMappings(mapper -> mapper.skip(User::setId))
                .map(request);
        user.setPassword(SimplePasswordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setRole("USER");

        userRepository.save(user);
    }

    @Override
    public UserResponse getById(Integer id) {
        ValidationUtil.checkId(id, userRepository);

        User user = userRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return user.mapToResponse();
    }

    @Override
    public List<UserResponse> getAll() {
        if (userRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<UserResponse> responses = new ArrayList<>();
            List<User> all = userRepository.findAll();

            all.forEach(user -> responses.add(user.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateUserRequest request, Integer id) {
        ValidationUtil.checkId(id, userRepository);

        User user = userRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.getUsername() != null && !request.getUsername().isEmpty() && !request.getUsername().equals(user.getUsername())) {
            checkUniqueUsername(request.getUsername());
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty() && !request.getEmail().equals(user.getEmail())) {
            checkUniqueEmail(request.getEmail());
            user.setEmail(request.getEmail());
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
    public SimpleTextResponse loginUser(UserLoginRequest request) {

        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail());
        if (user == null) {
            throw new IllegalArgumentException("Неверно имя пользователя или почта");
        }
        if (!SimplePasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }

        String token = jwtTokenUtil.generateToken(user);


        return new SimpleTextResponse("Вход выполнен. Ваш токен: " + token);
    }

    @Override
    public SimpleTextResponse recoverPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Неверная почта, пользователь не найден");
        }
        String tempPassword = generateTemporaryPassword();
        user.setPassword(SimplePasswordEncoder.encode(tempPassword));
        userRepository.save(user);

        return new SimpleTextResponse("Отправка временного пароля на почту " + email + ": " + tempPassword);
    }

    @Override
    public void resetPassword(UserResetPasswordRequest request) {
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail());
        if (user == null) {
            throw new IllegalArgumentException("Неверно имя пользователя или почта");
        }
        if (!SimplePasswordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }
        user.setPassword(SimplePasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private void checkUniqueUsername(String username) {
        try {
            Optional<User> byUsername = userRepository.findByUsername(username);
            if (byUsername.isPresent()) {
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
