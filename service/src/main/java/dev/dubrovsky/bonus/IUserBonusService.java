package dev.dubrovsky.bonus;

public interface IUserBonusService {

    void create(UserBonus entity);

    void getAll();

    void delete(Integer userId, Integer bonusId);

}
