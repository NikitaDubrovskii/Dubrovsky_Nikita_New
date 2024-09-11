package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        loyaltyProgram.setDescription(request.description());

        validateLoyaltyProgram(loyaltyProgram);

        loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Override
    public LoyaltyProgramResponse getById(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramRepository);

        LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findById(id).orElse(null);
        return loyaltyProgram.mapToResponse();
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
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setName(request.name());
        loyaltyProgram.setDescription(request.description());

        validateLoyaltyProgram(loyaltyProgram);
        ValidationUtil.checkId(id, loyaltyProgramRepository);

        loyaltyProgram.setId(id);
        loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramRepository);
        loyaltyProgramRepository.deleteById(id);
    }

    private void validateLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        if (loyaltyProgram == null) {
            throw new IllegalArgumentException("Программа лояльности не может отсутствовать");
        }
        if (loyaltyProgram.getName() == null || loyaltyProgram.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Название должно быть");
        }
    }

}
