package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.bonus.Bonus;

public interface IBonusService extends ICommonService<Bonus, NewBonusRequest, UpdateBonusRequest> {
}
