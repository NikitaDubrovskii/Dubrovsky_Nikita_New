package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.bonus.Bonus;

public interface IBonusService extends ICommonService<BonusResponse, NewBonusRequest, UpdateBonusRequest> {
}
