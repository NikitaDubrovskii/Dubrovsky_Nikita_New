package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import dev.dubrovsky.service.loyalty.program.LoyaltyProgramService;

public abstract class AbstractLoyaltyProgramController extends AbstractController<LoyaltyProgramService, LoyaltyProgramResponse, NewLoyaltyProgramRequest, UpdateLoyaltyProgramRequest> {

    public AbstractLoyaltyProgramController(LoyaltyProgramService service) {
        super(service);
    }

}
