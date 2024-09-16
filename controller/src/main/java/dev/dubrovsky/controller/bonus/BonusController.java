package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.service.bonus.BonusService;
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
@RequestMapping("api/v1/bonus")
@AllArgsConstructor
@Tag(name="Бонусы", description="Взаимодействие с бонусами")
public class BonusController {

    private final BonusService bonusService;

    @Operation(summary = "Создание бонусов", description = "Создание бонусов")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewBonusRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            bonusService.create(request);
            return new ResponseEntity<>("Создано!", HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение бонусов", description = "Получение бонусов по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(bonusService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение списка бонусов", description = "Получение списка бонусов")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(bonusService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Обновление бонуса", description = "Обновление бонуса по id")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateBonusRequest request,
                                    @PathVariable Integer id) {
        bonusService.update(request, id);
        return new ResponseEntity<>("Обновлено!", HttpStatus.OK);
    }

    @Operation(summary = "Удаление бонуса", description = "Удаление бонуса по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        bonusService.delete(id);
        return new ResponseEntity<>("Удалено!", HttpStatus.OK);
    }

}
