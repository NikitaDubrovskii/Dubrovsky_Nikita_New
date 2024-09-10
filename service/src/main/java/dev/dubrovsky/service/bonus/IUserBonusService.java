package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import dev.dubrovsky.model.bonus.UserBonus;

import java.util.List;

public interface IUserBonusService {

    void create(NewUserBonusRequest request);

    List<UserBonus> getAll();

    void delete(Integer userId, Integer bonusId);

}
