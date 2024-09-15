package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LoyaltyProgramService implements ILoyaltyProgramService {

    private final LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public void create(NewLoyaltyProgramRequest request) {
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setName(request.name());
        if (request.description() != null && !request.description().isEmpty()) {
            loyaltyProgram.setDescription(request.description());
        }
        loyaltyProgram.setCreatedAt(LocalDateTime.now());

        loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Override
    public LoyaltyProgramResponse getById(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramRepository);

        LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findById(id).orElse(null);
        return loyaltyProgram != null ? loyaltyProgram.mapToResponse() : null;
    }

    @Override
    public List<LoyaltyProgramResponse> getAll() {
        if (loyaltyProgramRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<LoyaltyProgramResponse> responses = new ArrayList<>();
            List<LoyaltyProgram> all = loyaltyProgramRepository.findAll();

            all.forEach(loyaltyProgram -> responses.add(loyaltyProgram.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateLoyaltyProgramRequest request, Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramRepository);

        LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findById(id).orElse(null);
        assert loyaltyProgram != null;

        if (request.name() != null && !request.name().isEmpty()) {
            loyaltyProgram.setName(request.name());
        }
        if (request.description() != null && !request.description().isEmpty()) {
            loyaltyProgram.setDescription(request.description());
        }
        loyaltyProgram.setId(id);

        loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramRepository);
        loyaltyProgramRepository.deleteById(id);
    }

}
