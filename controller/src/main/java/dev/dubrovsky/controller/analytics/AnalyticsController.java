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
    @Operation(summary = "Создание аналитики (admin)", description = "Создание аналитики, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewAnalyticsRequest request, BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение аналитики (admin)", description = "Получение аналитики по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка аналитики (admin)", description = "Получение списка аналитики, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление аналитики (admin)", description = "Обновление аналитики по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id, UpdateAnalyticsRequest request, BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление аналитики (admin)", description = "Удаление аналитики по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
