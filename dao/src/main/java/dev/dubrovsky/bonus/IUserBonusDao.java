package dev.dubrovsky.bonus;

import java.util.List;

public interface IUserBonusDao {

    void create(UserBonus entity);

    List<UserBonus> getAll();

    void delete(Integer userId, Integer bonusId);

}
