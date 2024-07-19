package dev.dubrovsky.service.loyalty_program;

import dev.dubrovsky.model.loyalty_program.UserLoyaltyProgram;

public interface IUserLoyaltyProgramService {

    void create(UserLoyaltyProgram entity);

    void getAll();

    void delete(Integer userId, Integer programId);

}
