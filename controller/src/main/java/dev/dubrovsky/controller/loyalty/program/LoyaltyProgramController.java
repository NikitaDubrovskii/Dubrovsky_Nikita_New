package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.service.loyalty.program.LoyaltyProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/loyalty-program")
@Tag(name = "Программы лояльности", description = "Взаимодействие с программой лояльности")
public class LoyaltyProgramController extends AbstractLoyaltyProgramController {

    private final LoyaltyProgramService loyaltyProgramService;

    public LoyaltyProgramController(LoyaltyProgramService loyaltyProgramService) {
        super(loyaltyProgramService);
        this.loyaltyProgramService = loyaltyProgramService;
    }

    @Override
    @Operation(summary = "Создание программы лояльности", description = "Создание программы лояльности")
    public ResponseEntity<?> create(NewLoyaltyProgramRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение программы лояльности", description = "Получение программы лояльности по id")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка программ лояльности", description = "Получение списка программ лояльности")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление программы лояльности", description = "Обновление программы лояльности по id")
    public ResponseEntity<?> update(UpdateLoyaltyProgramRequest request,
                                    Integer id,
                                    BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление программы лояльности", description = "Удаление программы лояльности по id")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
