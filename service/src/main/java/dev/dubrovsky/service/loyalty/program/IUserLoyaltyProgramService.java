package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;

public interface IUserLoyaltyProgramService {

    void create(UserLoyaltyProgram entity);

    void getAll();

    void delete(Integer userId, Integer programId);

}
