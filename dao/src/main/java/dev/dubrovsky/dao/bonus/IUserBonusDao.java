package dev.dubrovsky.dao.bonus;

import dev.dubrovsky.model.bonus.UserBonus;

import java.util.List;

public interface IUserBonusDao {

    void create(UserBonus entity);

    List<UserBonus> getAll();

    void delete(Integer userId, Integer bonusId);

}
