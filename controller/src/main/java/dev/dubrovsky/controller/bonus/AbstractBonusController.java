package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.service.bonus.BonusService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractBonusController extends AbstractController<BonusService, BonusResponse, NewBonusRequest, UpdateBonusRequest> {

    public AbstractBonusController(BonusService service) {
        super(service);
    }

    @GetMapping("/my")
    public abstract ResponseEntity<?> getBonusesByUser(Authentication authentication);

    @GetMapping("/my/{id}")
    public abstract ResponseEntity<?> getOneByUser(Authentication authentication, @PathVariable Integer id);

}
