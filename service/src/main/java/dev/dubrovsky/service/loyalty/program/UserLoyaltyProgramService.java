package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgramId;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.repository.loyalty.program.UserLoyaltyProgramRepository;
import dev.dubrovsky.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserLoyaltyProgramService implements IUserLoyaltyProgramService {

    private final UserLoyaltyProgramRepository userLoyaltyProgramRepository;
    private final UserRepository userRepository;
    private final LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public void create(UserLoyaltyProgram userLoyaltyProgram) {
        validateUserLoyaltyProgram(userLoyaltyProgram);
        checkId(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId(), userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId());

        userLoyaltyProgramRepository.save(userLoyaltyProgram);
    }

    @Override
    public void getAll() {
        if (userLoyaltyProgramRepository.findAll().isEmpty()) {
            System.out.println("Таблица программ лояльности пользователя пустая");
        } else {
            userLoyaltyProgramRepository.findAll().forEach(System.out::println);
        }
    }

    @Override
    public void delete(Integer userId, Integer programId) {
        checkId(userId, programId);
        UserLoyaltyProgramId userLoyaltyProgramId = new UserLoyaltyProgramId(userId, programId);

        userLoyaltyProgramRepository.deleteById(userLoyaltyProgramId);
    }

    private void validateUserLoyaltyProgram(UserLoyaltyProgram userLoyaltyProgram) {
        if (userLoyaltyProgram == null) {
            throw new IllegalArgumentException("Программа лояльности пользователя не может быть пустой");
        }
    }

    private void checkId(Integer userId, Integer programId) {
        if (userId > 0) {
            userRepository
                    .findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("Пользователь не найден с id: " + userId));
            /*Optional
                    .ofNullable(userRepository.getById(userId))
                    .orElseThrow(() -> new NoSuchElementException("Пользователь не найден с id: " + userId));*/
        }
        if (programId > 0) {
            loyaltyProgramRepository
                    .findById(programId)
                    .orElseThrow(() -> new NoSuchElementException("Программа лояльности не найдена с id: " + programId));
            /*Optional
                    .ofNullable(loyaltyProgramRepository.getById(programId))
                    .orElseThrow(() -> new NoSuchElementException("Программа лояльности не найдена с id: " + programId));*/
        }
        if (userId < 1 || programId < 1) {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
