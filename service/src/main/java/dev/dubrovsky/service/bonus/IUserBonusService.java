package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import dev.dubrovsky.dto.response.bonus.UserBonusResponse;

import java.util.List;

public interface IUserBonusService {

    void create(NewUserBonusRequest request);

    List<UserBonusResponse> getAll();

    void delete(Integer userId, Integer bonusId);

}
