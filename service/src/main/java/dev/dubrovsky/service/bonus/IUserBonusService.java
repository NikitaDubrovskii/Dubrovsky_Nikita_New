package dev.dubrovsky.service.bonus;

import dev.dubrovsky.model.bonus.UserBonus;

public interface IUserBonusService {

    void create(UserBonus entity);

    void getAll();

    void delete(Integer userId, Integer bonusId);

}
