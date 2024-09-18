package dev.dubrovsky.controller.user;

import dev.dubrovsky.dto.request.user.NewUserRequest;
import dev.dubrovsky.dto.request.user.UpdateUserRequest;
import dev.dubrovsky.dto.request.user.UserLoginRequest;
import dev.dubrovsky.dto.request.user.UserResetPasswordRequest;
import dev.dubrovsky.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
public class UserController extends AbstractUserController {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @Override
    @Operation(summary = "Создание пользователя", description = "Создание пользователя")
    public ResponseEntity<?> create(NewUserRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение пользователя", description = "Получение пользователя по id")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка пользователей", description = "Получение списка пользователей")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление пользователя", description = "Обновление пользователя по id")
    public ResponseEntity<?> update(UpdateUserRequest request,
                                    Integer id,
                                    BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление пользователя", description = "Удаление пользователя по id")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    @Operation(summary = "Вход пользователя", description = "Вход пользователя по username/email и password")
    public ResponseEntity<?> login(UserLoginRequest request,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
        }
    }

    @Override
    @Operation(summary = "Восстановление пароля", description = "Восстановление пароля по email")
    public ResponseEntity<?> recoverPassword(@RequestParam(name = "email") String email) {
        return new ResponseEntity<>(userService.recoverPassword(email), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Сброс пароля", description = "Сброс пароля по username/email и password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid UserResetPasswordRequest request,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            userService.resetPassword(request);
            return new ResponseEntity<>("Пароль изменен!", HttpStatus.OK);
        }
    }

}
