package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewUserLoyaltyProgramRequest;
import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import dev.dubrovsky.dto.response.loyalty.program.UserLoyaltyProgramResponse;
import dev.dubrovsky.dto.response.user.UserResponse;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgramId;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.repository.loyalty.program.UserLoyaltyProgramRepository;
import dev.dubrovsky.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserLoyaltyProgramService implements IUserLoyaltyProgramService {

    private final UserLoyaltyProgramRepository userLoyaltyProgramRepository;
    private final UserRepository userRepository;
    private final LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public void create(NewUserLoyaltyProgramRequest request) {
        checkId(request.userId(), request.programId());

        UserLoyaltyProgram userLoyaltyProgram = new UserLoyaltyProgram();
        UserLoyaltyProgramId userLoyaltyProgramId = new UserLoyaltyProgramId();
        userLoyaltyProgramId.setProgramId(request.programId());
        userLoyaltyProgramId.setUserId(request.userId());
        userLoyaltyProgram.setUserLoyaltyProgramId(userLoyaltyProgramId);
        userLoyaltyProgram.setReceivedAt(LocalDateTime.now());

        userLoyaltyProgramRepository.save(userLoyaltyProgram);
    }

    @Override
    public List<UserLoyaltyProgramResponse> getAll() {
        if (userLoyaltyProgramRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<UserLoyaltyProgramResponse> responses = new ArrayList<>();
            List<UserLoyaltyProgram> all = userLoyaltyProgramRepository.findAll();

            all.forEach(userLoyaltyProgram -> responses.add(mapToResponse(userLoyaltyProgram)));

            return responses;
        }
    }

    @Override
    public void delete(Integer userId, Integer programId) {
        checkId(userId, programId);
        UserLoyaltyProgramId userLoyaltyProgramId = new UserLoyaltyProgramId(userId, programId);

        userLoyaltyProgramRepository.deleteById(userLoyaltyProgramId);
    }

    private void checkId(Integer userId, Integer programId) {
        if (userId > 0) {
            userRepository
                    .findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с id: " + userId));
        }
        if (programId > 0) {
            loyaltyProgramRepository
                    .findById(programId)
                    .orElseThrow(() -> new EntityNotFoundException("Программа лояльности не найдена с id: " + programId));
        }
        if (userId < 1 || programId < 1) {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private UserLoyaltyProgramResponse mapToResponse(UserLoyaltyProgram userLoyaltyProgram) {
        User user = userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId()).orElse(null);
        LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId()).orElse(null);

        assert user != null;
        assert loyaltyProgram != null;
        UserResponse userResponse = user.mapToResponse();
        LoyaltyProgramResponse loyaltyProgramResponse = loyaltyProgram.mapToResponse();

        return new UserLoyaltyProgramResponse(userResponse, loyaltyProgramResponse, userLoyaltyProgram.getReceivedAt());
    }

}
