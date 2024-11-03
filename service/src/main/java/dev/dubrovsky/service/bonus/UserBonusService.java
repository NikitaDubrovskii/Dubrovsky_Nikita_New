package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.dto.response.bonus.UserBonusResponse;
import dev.dubrovsky.dto.response.user.UserResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.bonus.UserBonus;
import dev.dubrovsky.model.bonus.UserBonusId;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.bonus.BonusRepository;
import dev.dubrovsky.repository.bonus.UserBonusRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.service.analytics.AnalyticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserBonusService implements IUserBonusService {

    private final UserBonusRepository userBonusRepository;
    private final UserRepository userRepository;
    private final BonusRepository bonusRepository;
    private final AnalyticsService analyticsService;

    @Override
    public void create(NewUserBonusRequest request) {
        checkId(request.getBonusId(), request.getUserId());

        UserBonus userBonus = new UserBonus();
        UserBonusId userBonusId = new UserBonusId();
        userBonusId.setBonusId(request.getBonusId());
        userBonusId.setUserId(request.getUserId());
        userBonus.setUserBonusId(userBonusId);
        userBonus.setReceivedAt(LocalDateTime.now());

        userBonusRepository.save(userBonus);
        analyticsService.create(new NewAnalyticsRequest("Получен бонус", request.getUserId()));
    }

    @Override
    public List<UserBonusResponse> getAll() {
        if (userBonusRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<UserBonusResponse> responses = new ArrayList<>();
            List<UserBonus> all = userBonusRepository.findAll();

            all.forEach(userBonus -> responses.add(mapToResponse(userBonus)));

            return responses;
        }
    }

    @Override
    public void delete(Integer userId, Integer bonusId) {
        checkId(userId, bonusId);
        UserBonusId userBonusId = new UserBonusId(userId, bonusId);

        userBonusRepository.deleteById(userBonusId);
    }

    private void checkId(Integer userId, Integer bonusId) {
        if (userId > 0) {
            userRepository
                    .findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с id: " + userId));
        }
        if (bonusId > 0) {
            bonusRepository
                    .findById(bonusId)
                    .orElseThrow(() -> new EntityNotFoundException("Бонус не найден с id: " + userId));
        }
        if (userId < 1 || bonusId < 1) {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private UserBonusResponse mapToResponse(UserBonus userBonus) {
        User user = userRepository.findById(userBonus.getUserBonusId().getUserId()).orElseThrow(DbResponseErrorException::new);
        Bonus bonus = bonusRepository.findById(userBonus.getUserBonusId().getBonusId()).orElseThrow(DbResponseErrorException::new);

        UserResponse userResponse = user.mapToResponse();
        BonusResponse bonusResponse = bonus.mapToResponse();

        return new UserBonusResponse(userResponse, bonusResponse, userBonus.getReceivedAt());
    }

}
