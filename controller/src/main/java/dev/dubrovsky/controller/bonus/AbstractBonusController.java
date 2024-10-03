package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.service.bonus.BonusService;

public abstract class AbstractBonusController extends AbstractController<BonusService, BonusResponse, NewBonusRequest, UpdateBonusRequest> {

    public AbstractBonusController(BonusService service) {
        super(service);
    }

}
