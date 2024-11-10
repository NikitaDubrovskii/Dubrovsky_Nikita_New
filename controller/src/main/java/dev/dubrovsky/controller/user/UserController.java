package dev.dubrovsky.controller.user;

import dev.dubrovsky.dto.request.user.NewUserRequest;
import dev.dubrovsky.dto.request.user.UpdateUserRequest;
import dev.dubrovsky.dto.request.user.UserLoginRequest;
import dev.dubrovsky.dto.request.user.UserResetPasswordRequest;
import dev.dubrovsky.service.user.UserService;
import dev.dubrovsky.util.response.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "Создание пользователя (admin)", description = "Создание пользователя, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewUserRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение пользователя (admin)", description = "Получение пользователя по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка пользователей (admin)", description = "Получение списка пользователей, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление пользователя (admin)", description = "Обновление пользователя по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id,
                                    UpdateUserRequest request,
                                    BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление пользователя (admin)", description = "Удаление пользователя по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    @Operation(summary = "Регистрация пользователя (public)", description = "Регистрация пользователя, доступно незарегистрированным пользователям")
    public ResponseEntity<?> register(NewUserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.generateErrorResponse(bindingResult);
        } else {
            return super.create(request, bindingResult);
        }
    }

    @Override
    @Operation(summary = "Вход пользователя (public)", description = "Вход пользователя по username/email и password, доступно незарегистрированным пользователям")
    public ResponseEntity<?> login(UserLoginRequest request,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.generateErrorResponse(bindingResult);
        } else {
            return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
        }
    }

    @Override
    @Operation(summary = "Восстановление пароля (public)", description = "Восстановление пароля по email, доступно незарегистрированным пользователям")
    public ResponseEntity<?> recoverPassword(@RequestParam(name = "email") String email) {
        return new ResponseEntity<>(userService.recoverPassword(email), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Сброс пароля (public)", description = "Сброс пароля по username/email и password, доступно незарегистрированным пользователям")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid UserResetPasswordRequest request,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.generateErrorResponse(bindingResult);
        } else {
            userService.resetPassword(request);
            return new ResponseEntity<>("Пароль изменен!", HttpStatus.OK);
        }
    }

    @Override
    @Operation(summary = "Получение себя (user)", description = "Получение самого себя, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getYourself(Authentication authentication) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getYourself(username), HttpStatus.OK);
    }

}
