package dev.dubrovsky.controller.user;

import dev.dubrovsky.dto.request.user.NewUserRequest;
import dev.dubrovsky.dto.request.user.UpdateUserRequest;
import dev.dubrovsky.dto.request.user.UserLoginRequest;
import dev.dubrovsky.dto.request.user.UserResetPasswordRequest;
import dev.dubrovsky.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewUserRequest request) {
        userService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest request,
                                    @PathVariable Integer id) {
        userService.update(request, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        userService.loginUser(request.usernameOrEmail(), request.password());
        return new ResponseEntity<>(HttpStatus.OK);

        /*try {
            authService.loginUser(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
            return ResponseEntity.ok("Вход выполнен");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }*/
    }

    @PostMapping("/recover-password")
    public ResponseEntity<?> recoverPassword(@RequestParam(name = "email") String email) {
        userService.recoverPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);

        /*try {
            authService.recoverPassword(request.getEmail());
            return ResponseEntity.ok("Временный пароль отправлен на почту");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }*/
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody UserResetPasswordRequest request) {
        userService.resetPassword(request.usernameOrEmail(), request.oldPassword(), request.newPassword());
        return new ResponseEntity<>(HttpStatus.OK);

        /*try {
            authService.resetPassword(request.getUsernameOrEmail(), request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok("Пароль успешно изменен");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }*/
    }

}
