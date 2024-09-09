package dev.dubrovsky.service.bonus;

import dev.dubrovsky.model.bonus.UserBonus;

import java.util.List;

public interface IUserBonusService {

    void create(UserBonus entity);

    List<UserBonus> getAll();

    void delete(Integer userId, Integer bonusId);

}
