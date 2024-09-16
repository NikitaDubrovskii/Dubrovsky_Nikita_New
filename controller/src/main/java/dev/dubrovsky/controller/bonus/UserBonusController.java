package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import dev.dubrovsky.service.bonus.UserBonusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user-bonus")
@AllArgsConstructor
@Tag(name="Бонусы пользователя", description="Взаимодействие с бонусами пользователя")
public class UserBonusController {

    private final UserBonusService userBonusService;

    @Operation(summary = "Создание бонуса пользователя", description = "Создание бонуса пользователя")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewUserBonusRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            userBonusService.create(request);
            return new ResponseEntity<>("Создано!", HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение списка бонусов пользователей", description = "Получение списка бонусов пользователей")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userBonusService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Удаление бонуса пользователя", description = "Удаление бонуса пользователя по id пользователя и id бонуса")
    @DeleteMapping("/{userId}/{bonusId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId,
                                    @PathVariable Integer bonusId) {
        userBonusService.delete(userId, bonusId);
        return new ResponseEntity<>("Удалено!", HttpStatus.OK);
    }

}
