package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.repository.bonus.BonusRepository;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BonusService implements IBonusService {

    private final BonusRepository bonusRepository;
    private final LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public void create(NewBonusRequest request) {
        Bonus bonus = new Bonus();
        bonus.setName(request.name());
        bonus.setDescription(request.description());
        bonus.setProgram(loyaltyProgramRepository
                .findById(request.programId())
                .orElse(null));
        bonus.setPoints(request.points());

        validateBonus(bonus);
        ValidationUtil.checkEntityPresent(bonus.getProgram().getId(), loyaltyProgramRepository);

        bonusRepository.save(bonus);
    }

    @Override
    public BonusResponse getById(Integer id) {
        ValidationUtil.checkId(id, bonusRepository);

        Bonus bonus = bonusRepository.findById(id).orElse(null);

        return bonus.mapToResponse();
    }

    @Override
    public List<BonusResponse> getAll() {
        if (bonusRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<BonusResponse> responses = new ArrayList<>();
            List<Bonus> all = bonusRepository.findAll();

            all.forEach(bonus -> responses.add(bonus.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateBonusRequest request, Integer id) {
        Bonus bonus = new Bonus();
        bonus.setName(request.name());
        bonus.setDescription(request.description());
        bonus.setProgram(loyaltyProgramRepository
                .findById(request.programId())
                .orElse(null));
        bonus.setPoints(request.points());

        validateBonus(bonus);
        ValidationUtil.checkId(id, bonusRepository);
        ValidationUtil.checkEntityPresent(bonus.getProgram().getId(), loyaltyProgramRepository);

        bonus.setId(id);
        bonusRepository.save(bonus);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, bonusRepository);
        bonusRepository.deleteById(id);
    }

    private void validateBonus(Bonus bonus) {
        if (bonus == null) {
            throw new IllegalArgumentException("Бонус не может отсутствовать");
        }
        if (bonus.getName() == null || bonus.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Название должно быть");
        }
        if (bonus.getPoints() == null) {
            throw new IllegalArgumentException("Количество очков должно быть");
        }
    }

}
