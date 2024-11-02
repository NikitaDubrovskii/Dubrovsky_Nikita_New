package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;

import java.util.List;

public interface ILoyaltyProgramService extends ICommonService<LoyaltyProgramResponse, NewLoyaltyProgramRequest, UpdateLoyaltyProgramRequest> {

    List<LoyaltyProgramResponse> getProgramsByUser(String username);

    LoyaltyProgramResponse getOneByUser(String username, Integer id);

}
