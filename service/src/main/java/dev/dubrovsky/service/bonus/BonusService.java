package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
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
        ValidationUtil.checkEntityPresent(request.programId(), loyaltyProgramRepository);

        Bonus bonus = new Bonus();
        bonus.setName(request.name());
        bonus.setDescription(request.description() != null ? request.description() : "");
        bonus.setPoints(request.points());
        bonus.setProgram(loyaltyProgramRepository
                .findById(request.programId())
                .orElseThrow(DbResponseErrorException::new));

        bonusRepository.save(bonus);
    }

    @Override
    public BonusResponse getById(Integer id) {
        ValidationUtil.checkId(id, bonusRepository);

        Bonus bonus = bonusRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return bonus.mapToResponse();
    }

    @Override
    public List<BonusResponse> getAll() {
        if (bonusRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<BonusResponse> responses = new ArrayList<>();
            List<Bonus> all = bonusRepository.findAll();

            all.forEach(bonus -> responses.add(bonus.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateBonusRequest request, Integer id) {
        ValidationUtil.checkId(id, bonusRepository);

        Bonus bonus = bonusRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.name() != null && !request.name().isEmpty()) {
            bonus.setName(request.name());
        }
        if (request.description() != null && !request.description().isEmpty()) {
            bonus.setDescription(request.description());
        }
        if (request.points() != null) {
            bonus.setPoints(request.points());
        }
        if (request.programId() != null && request.programId() != 0) {
            bonus.setProgram(loyaltyProgramRepository.findById(request.programId()).orElseThrow(DbResponseErrorException::new));
        }
        bonus.setId(id);

        bonusRepository.save(bonus);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, bonusRepository);
        bonusRepository.deleteById(id);
    }

}
