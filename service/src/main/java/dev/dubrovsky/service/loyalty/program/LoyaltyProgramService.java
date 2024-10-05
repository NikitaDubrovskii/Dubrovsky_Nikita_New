package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LoyaltyProgramService implements ILoyaltyProgramService {

    private final LoyaltyProgramRepository loyaltyProgramRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public void create(NewLoyaltyProgramRequest request) {
        LoyaltyProgram loyaltyProgram = mapper.map(request, LoyaltyProgram.class);
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            loyaltyProgram.setDescription(request.getDescription());
        }
        loyaltyProgram.setCreatedAt(LocalDateTime.now());

        loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Override
    public LoyaltyProgramResponse getById(Integer id) {
        ValidationUtil.checkId(id, loyaltyProgramRepository);

        LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return loyaltyProgram.mapToResponse();
    }

    @Override
    public List<LoyaltyProgramResponse> getAll() {
        if (loyaltyProgramRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
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

        LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.getName() != null && !request.getName().isEmpty()) {
            loyaltyProgram.setName(request.getName());
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            loyaltyProgram.setDescription(request.getDescription());
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
