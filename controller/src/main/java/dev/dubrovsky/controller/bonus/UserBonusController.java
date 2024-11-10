package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import dev.dubrovsky.service.bonus.UserBonusService;
import dev.dubrovsky.util.response.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user-bonus")
@AllArgsConstructor
@Tag(name = "Бонусы пользователя", description = "Взаимодействие с бонусами пользователя")
public class UserBonusController extends AbstractUserBonusController {

    private final UserBonusService userBonusService;

    @Override
    @Operation(summary = "Создание бонуса пользователя (admin)", description = "Создание бонуса пользователя, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewUserBonusRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.generateErrorResponse(bindingResult);
        } else {
            userBonusService.create(request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Override
    @Operation(summary = "Получение списка бонусов пользователей (admin)", description = "Получение списка бонусов пользователей, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userBonusService.getAll(), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Удаление бонуса пользователя (admin)", description = "Удаление бонуса пользователя по id пользователя и id бонуса, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer userId,
                                    Integer bonusId) {
        userBonusService.delete(userId, bonusId);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
