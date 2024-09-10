package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;

public interface ILoyaltyProgramService extends ICommonService<LoyaltyProgram, NewLoyaltyProgramRequest, UpdateLoyaltyProgramRequest> {
}
