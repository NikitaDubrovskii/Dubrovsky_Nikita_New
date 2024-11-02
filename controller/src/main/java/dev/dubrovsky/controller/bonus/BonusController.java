package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.service.bonus.BonusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bonus")
@Tag(name = "Бонусы", description = "Взаимодействие с бонусами")
public class BonusController extends AbstractBonusController {

    public BonusController(BonusService bonusService) {
        super(bonusService);
    }

    @Override
    @Operation(summary = "Создание бонусов", description = "Создание бонусов")
    public ResponseEntity<?> create(NewBonusRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение бонусов", description = "Получение бонусов по id")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка бонусов", description = "Получение списка бонусов")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление бонуса", description = "Обновление бонуса по id")
    public ResponseEntity<?> update(UpdateBonusRequest request,
                                    Integer id,
                                    BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление бонуса", description = "Удаление бонуса по id")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<?> getBonusesByUser(Authentication authentication) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getBonusesByUser(username), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getOneByUser(Authentication authentication, Integer id) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOneByUser(username, id), HttpStatus.OK);
    }

}
