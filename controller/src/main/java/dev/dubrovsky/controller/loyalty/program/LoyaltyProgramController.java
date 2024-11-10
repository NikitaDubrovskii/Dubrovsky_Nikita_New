package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.service.loyalty.program.LoyaltyProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/loyalty-program")
@Tag(name = "Программы лояльности", description = "Взаимодействие с программой лояльности")
public class LoyaltyProgramController extends AbstractLoyaltyProgramController {

    public LoyaltyProgramController(LoyaltyProgramService loyaltyProgramService) {
        super(loyaltyProgramService);
    }

    @Override
    @Operation(summary = "Создание программы лояльности (admin)", description = "Создание программы лояльности, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewLoyaltyProgramRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение программы лояльности (admin)", description = "Получение программы лояльности по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка программ лояльности (admin)", description = "Получение списка программ лояльности, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Получение списка программ лояльности (public)", description = "Получение списка программ лояльности, доступно незарегистрированным пользователям")
    public ResponseEntity<?> getAllPublic() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Получение программы лояльности (public)", description = "Получение программы лояльности по id, доступно незарегистрированным пользователям")
    public ResponseEntity<?> getByIdPublic(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Обновление программы лояльности (admin)", description = "Обновление программы лояльности по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id,
                                    UpdateLoyaltyProgramRequest request,
                                    BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление программы лояльности (admin)", description = "Удаление программы лояльности по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    @Operation(summary = "Получение списка программ лояльности (user)", description = "Получение списка программ лояльности, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getProgramsByUser(Authentication authentication) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getProgramsByUser(username), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Получение программы лояльности (user)", description = "Получение программы лояльности по id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getOneByUser(Authentication authentication, Integer id) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOneByUser(username, id), HttpStatus.OK);
    }

}
