package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.service.ICommonService;

import java.util.List;

public interface IBonusService extends ICommonService<BonusResponse, NewBonusRequest, UpdateBonusRequest> {

    List<BonusResponse> getBonusesByUser(String username);

    BonusResponse getOneByUser(String username, Integer id);

}
