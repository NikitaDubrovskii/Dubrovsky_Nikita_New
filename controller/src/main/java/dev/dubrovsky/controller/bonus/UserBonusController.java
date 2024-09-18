package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import dev.dubrovsky.service.bonus.UserBonusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user-bonus")
@AllArgsConstructor
@Tag(name = "Бонусы пользователя", description = "Взаимодействие с бонусами пользователя")
public class UserBonusController extends AbstractUserBonusController {

    private final UserBonusService userBonusService;

    @Override
    @Operation(summary = "Создание бонуса пользователя", description = "Создание бонуса пользователя")
    public ResponseEntity<?> create(NewUserBonusRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            userBonusService.create(request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Override
    @Operation(summary = "Получение списка бонусов пользователей", description = "Получение списка бонусов пользователей")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userBonusService.getAll(), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Удаление бонуса пользователя", description = "Удаление бонуса пользователя по id пользователя и id бонуса")
    public ResponseEntity<?> delete(Integer userId,
                                    Integer bonusId) {
        userBonusService.delete(userId, bonusId);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
