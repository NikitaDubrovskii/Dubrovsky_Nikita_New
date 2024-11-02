package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import dev.dubrovsky.service.loyalty.program.LoyaltyProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractLoyaltyProgramController extends AbstractController<LoyaltyProgramService, LoyaltyProgramResponse, NewLoyaltyProgramRequest, UpdateLoyaltyProgramRequest> {

    public AbstractLoyaltyProgramController(LoyaltyProgramService service) {
        super(service);
    }

    @GetMapping("/public")
    public abstract ResponseEntity<?> getAllPublic();

    @GetMapping("/public/{id}")
    public abstract ResponseEntity<?> getByIdPublic(@PathVariable Integer id);

    @GetMapping("/my")
    public abstract ResponseEntity<?> getProgramsByUser(Authentication authentication);

    @GetMapping("/my/{id}")
    public abstract ResponseEntity<?> getOneByUser(Authentication authentication, @PathVariable Integer id);

}
