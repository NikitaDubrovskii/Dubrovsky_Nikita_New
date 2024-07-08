package dev.dubrovsky.loyalty_program;

public interface IUserLoyaltyProgramService {

    void create(UserLoyaltyProgram entity);

    void getAll();

    void delete(Integer userId, Integer programId);

}
