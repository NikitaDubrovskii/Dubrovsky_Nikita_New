package dev.dubrovsky.dao.loyalty_program;

import dev.dubrovsky.model.loyalty_program.UserLoyaltyProgram;

import java.util.List;

public interface IUserLoyaltyProgramDao {

    void create(UserLoyaltyProgram entity);

    List<UserLoyaltyProgram> getAll();

    void delete(Integer userId, Integer programId);

}
