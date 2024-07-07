package dev.dubrovsky.loyalty_program;

import java.util.List;

public interface IUserLoyaltyProgramDao {

    void create(UserLoyaltyProgram entity);

    List<UserLoyaltyProgram> getAll();

    void delete(Integer userId, Integer programId);

}
