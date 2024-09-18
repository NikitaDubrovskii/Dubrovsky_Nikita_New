package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.loyalty.program.NewUserLoyaltyProgramRequest;
import dev.dubrovsky.service.loyalty.program.UserLoyaltyProgramService;
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
@RequestMapping("api/v1/user-loyalty-program")
@AllArgsConstructor
@Tag(name = "Программы лояльности пользователя", description = "Взаимодействие с программами лояльности пользователей")
public class UserLoyaltyProgramController extends AbstractUserLoyaltyProgramController {

    private final UserLoyaltyProgramService loyaltyProgramService;

    @Override
    @Operation(summary = "Создание программы лояльности пользователя", description = "Создание программы лояльности пользователя")
    public ResponseEntity<?> create(NewUserLoyaltyProgramRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            loyaltyProgramService.create(request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Override
    @Operation(summary = "Получение списка программ лояльности пользователей", description = "Получение списка программ лояльности пользователей")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(loyaltyProgramService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Удаление программ лояльности пользователя", description = "Удаление программ лояльности пользователя по id пользователя и id программы лояльности")
    @Override
    public ResponseEntity<?> delete(Integer userId,
                                    Integer programId) {
        loyaltyProgramService.delete(userId, programId);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
