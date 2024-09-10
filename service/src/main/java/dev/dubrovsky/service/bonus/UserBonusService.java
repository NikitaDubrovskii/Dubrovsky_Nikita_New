package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import dev.dubrovsky.model.bonus.UserBonus;
import dev.dubrovsky.model.bonus.UserBonusId;
import dev.dubrovsky.repository.bonus.BonusRepository;
import dev.dubrovsky.repository.bonus.UserBonusRepository;
import dev.dubrovsky.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserBonusService implements IUserBonusService {

    private final UserBonusRepository userBonusRepository;
    private final UserRepository userRepository;
    private final BonusRepository bonusRepository;

    @Override
    public void create(NewUserBonusRequest request) {
        UserBonus userBonus = new UserBonus();
        UserBonusId userBonusId = new UserBonusId();
        userBonusId.setBonusId(request.bonusId());
        userBonusId.setUserId(request.userId());
        userBonus.setUserBonusId(userBonusId);
        userBonus.setReceivedAt(LocalDateTime.now());

        validateUserBonus(userBonus);
        checkId(userBonus.getUserBonusId().getUserId(), userBonus.getUserBonusId().getBonusId());

        userBonusRepository.save(userBonus);
    }

    @Override
    public List<UserBonus> getAll() {
        if (userBonusRepository.findAll().isEmpty()) {
            return null;
        } else {
            return userBonusRepository.findAll();
        }
    }

    @Override
    public void delete(Integer userId, Integer bonusId) {
        checkId(userId, bonusId);
        UserBonusId userBonusId = new UserBonusId(userId, bonusId);

        userBonusRepository.deleteById(userBonusId);
    }

    private void validateUserBonus(UserBonus userBonus) {
        if (userBonus == null) {
            throw new IllegalArgumentException("Бонус пользователя не может отсутствовать");
        }
    }

    private void checkId(Integer userId, Integer bonusId) {
        if (userId > 0) {
            userRepository
                    .findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("Пользователь не найден с id: " + userId));
        }
        if (bonusId > 0) {
            bonusRepository
                    .findById(bonusId)
                    .orElseThrow(() -> new NoSuchElementException("Бонус не найден с id: " + userId));
        }
        if (userId < 1 || bonusId < 1) {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
