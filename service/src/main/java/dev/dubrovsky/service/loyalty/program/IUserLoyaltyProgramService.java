package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewUserLoyaltyProgramRequest;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;

import java.util.List;

public interface IUserLoyaltyProgramService {

    void create(NewUserLoyaltyProgramRequest request);

    List<UserLoyaltyProgram> getAll();

    void delete(Integer userId, Integer programId);

}
