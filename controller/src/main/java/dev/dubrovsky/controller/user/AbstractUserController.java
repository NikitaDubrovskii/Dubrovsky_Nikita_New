package dev.dubrovsky.controller.user;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.user.NewUserRequest;
import dev.dubrovsky.dto.request.user.UpdateUserRequest;
import dev.dubrovsky.dto.request.user.UserLoginRequest;
import dev.dubrovsky.dto.request.user.UserResetPasswordRequest;
import dev.dubrovsky.dto.response.user.UserResponse;
import dev.dubrovsky.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class AbstractUserController extends AbstractController<UserService, UserResponse, NewUserRequest, UpdateUserRequest> {

    public AbstractUserController(UserService service) {
        super(service);
    }

    @PostMapping("/register")
    public abstract ResponseEntity<?> register(@RequestBody @Valid NewUserRequest request, BindingResult bindingResult);

    @PostMapping("/login")
    public abstract ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest request, BindingResult bindingResult);

    @PostMapping("/recover-password")
    public abstract ResponseEntity<?> recoverPassword(@RequestParam(name = "email") String email);

    @PostMapping("/reset-password")
    public abstract ResponseEntity<?> resetPassword(@RequestBody @Valid UserResetPasswordRequest request, BindingResult bindingResult);

    @GetMapping("/me")
    public abstract ResponseEntity<?> getYourself(Authentication authentication);

}
