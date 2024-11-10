package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dto.request.bonus.NewBonusRequest;
import dev.dubrovsky.dto.request.bonus.UpdateBonusRequest;
import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.bonus.BonusRepository;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BonusService implements IBonusService {

    private final BonusRepository bonusRepository;
    private final LoyaltyProgramRepository loyaltyProgramRepository;
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public void create(NewBonusRequest request) {
        ValidationUtil.checkEntityPresent(request.getProgramId(), loyaltyProgramRepository);

        Bonus bonus = mapper
                .typeMap(NewBonusRequest.class, Bonus.class)
                .addMappings(mapper -> mapper.skip(Bonus::setId))
                .map(request);
        bonus.setDescription(request.getDescription() != null ? request.getDescription() : "");
        bonus.setProgram(loyaltyProgramRepository
                .findById(request.getProgramId())
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

        if (request.getName() != null && !request.getName().isEmpty()) {
            bonus.setName(request.getName());
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            bonus.setDescription(request.getDescription());
        }
        if (request.getPoints() != null) {
            bonus.setPoints(request.getPoints());
        }
        if (request.getProgramId() != null && request.getProgramId() != 0) {
            bonus.setProgram(loyaltyProgramRepository.findById(request.getProgramId()).orElseThrow(DbResponseErrorException::new));
        }
        bonus.setId(id);

        bonusRepository.save(bonus);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, bonusRepository);
        bonusRepository.deleteById(id);
    }

    @Override
    public List<BonusResponse> getBonusesByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));

        if (user.getBonuses().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<BonusResponse> responses = new ArrayList<>();
            List<Bonus> bonuses = user.getBonuses();
            bonuses.forEach(bonus -> responses.add(bonus.mapToResponse()));
            return responses;
        }
    }

    @Override
    public BonusResponse getOneByUser(String username, Integer id) {
        ValidationUtil.checkId(id, bonusRepository);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found in the DB"));

        if (user.getBonuses().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            return user.getBonuses().stream()
                    .filter(bonus -> bonus.getId().equals(id))
                    .findFirst()
                    .orElseThrow(DbResponseErrorException::new)
                    .mapToResponse();
        }
    }

}
