package dev.dubrovsky.controller.analytics;

import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.analytics.UpdateAnalyticsRequest;
import dev.dubrovsky.service.analytics.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/analytics")
@Tag(name = "Аналитика", description = "Взаимодействие с аналитикой")
public class AnalyticsController extends AbstractAnalyticsController {

    public AnalyticsController(AnalyticsService analyticsService) {
        super(analyticsService);
    }

    @Override
    @Operation(summary = "Создание аналитики", description = "Создание аналитики")
    public ResponseEntity<?> create(NewAnalyticsRequest request, BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение аналитики", description = "Получение аналитики по id")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка аналитики", description = "Получение списка аналитики")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление аналитики", description = "Обновление аналитики по id")
    public ResponseEntity<?> update(UpdateAnalyticsRequest request, Integer id, BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление аналитики", description = "Удаление аналитики по id")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
