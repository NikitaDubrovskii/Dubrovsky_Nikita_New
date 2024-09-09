package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;

import java.util.List;

public interface IUserLoyaltyProgramService {

    void create(UserLoyaltyProgram entity);

    List<UserLoyaltyProgram> getAll();

    void delete(Integer userId, Integer programId);

}
