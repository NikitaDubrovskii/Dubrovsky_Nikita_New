package dev.dubrovsky.controller.analytics;

import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.analytics.UpdateAnalyticsRequest;
import dev.dubrovsky.service.analytics.AnalyticsService;
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
@RequestMapping("api/v1/analytics")
@AllArgsConstructor
@Tag(name="Аналитика", description="Взаимодействие с аналитикой")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Operation(summary = "Создание аналитики", description = "Создание аналитики")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewAnalyticsRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            analyticsService.create(request);
            return new ResponseEntity<>("Создано!", HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Получение аналитики", description = "Получение аналитики по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(analyticsService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получение списка аналитики", description = "Получение списка аналитики")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(analyticsService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Обновление аналитики", description = "Обновление аналитики по id")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateAnalyticsRequest request,
                                    @PathVariable Integer id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            analyticsService.update(request, id);
            return new ResponseEntity<>("Обновлено!", HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление аналитики", description = "Удаление аналитики по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        analyticsService.delete(id);
        return new ResponseEntity<>("Удалено!", HttpStatus.OK);
    }

}
