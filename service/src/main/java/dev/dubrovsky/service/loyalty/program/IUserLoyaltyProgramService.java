package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewUserLoyaltyProgramRequest;
import dev.dubrovsky.dto.response.loyalty.program.UserLoyaltyProgramResponse;

import java.util.List;

public interface IUserLoyaltyProgramService {

    void create(NewUserLoyaltyProgramRequest request);

    List<UserLoyaltyProgramResponse> getAll();

    void delete(Integer userId, Integer programId);

}
