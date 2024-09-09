package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgramId;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
import dev.dubrovsky.repository.loyalty.program.UserLoyaltyProgramRepository;
import dev.dubrovsky.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserLoyaltyProgramServiceTest {

    @Mock
    private UserLoyaltyProgramRepository userLoyaltyProgramRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @InjectMocks
    private UserLoyaltyProgramService userLoyaltyProgramService;

    private UserLoyaltyProgram userLoyaltyProgram;

    @BeforeEach
    void setUp() {
        userLoyaltyProgramService = new UserLoyaltyProgramService(userLoyaltyProgramRepository, userRepository, loyaltyProgramRepository);

        userLoyaltyProgram = new UserLoyaltyProgram(new UserLoyaltyProgramId(1, 1));
    }

    @Test
    void create_Success() {
        when(userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(Optional.of(new User()));
        when(loyaltyProgramRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId())).thenReturn(Optional.of(new LoyaltyProgram()));

        userLoyaltyProgramService.create(userLoyaltyProgram);

        verify(userLoyaltyProgramRepository).save(userLoyaltyProgram);
    }

    @Test
    void create_UserLoyaltyProgramIsNull_ThrowIllegalArgumentException() {
        userLoyaltyProgram = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userLoyaltyProgramService.create(userLoyaltyProgram));
        assertEquals("Программа лояльности пользователя не может быть пустой", exception.getMessage());
    }

    @Test
    void create_UserIdIsNull_ThrowNullPointerException() {
        userLoyaltyProgram.getUserLoyaltyProgramId().setUserId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> userLoyaltyProgramService.create(userLoyaltyProgram));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"userId\" is null", exception.getMessage());
    }

    @Test
    void create_UserIdNotFound_ThrowNoSuchElementException() {
        when(userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userLoyaltyProgramService.create(userLoyaltyProgram));
        assertEquals("Пользователь не найден с id: " + userLoyaltyProgram.getUserLoyaltyProgramId().getUserId(), exception.getMessage());
    }

    @Test
    void create_LoyaltyProgramIdIsNull_ThrowNullPointerException() {
        when(userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(Optional.of(new User()));
        userLoyaltyProgram.getUserLoyaltyProgramId().setProgramId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> userLoyaltyProgramService.create(userLoyaltyProgram));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"programId\" is null", exception.getMessage());
    }

    @Test
    void create_LoyaltyProgramIdNotFound_ThrowNoSuchElementException() {
        when(userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(Optional.of(new User()));
        when(loyaltyProgramRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userLoyaltyProgramService.create(userLoyaltyProgram));
        assertEquals("Программа лояльности не найдена с id: " + userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId(), exception.getMessage());
    }

    @Test
    void create_IdsIsNegative_ThrowIllegalArgumentException() {
        userLoyaltyProgram.getUserLoyaltyProgramId().setUserId(-1);
        userLoyaltyProgram.getUserLoyaltyProgramId().setProgramId(-1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userLoyaltyProgramService.create(userLoyaltyProgram));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getAll_Success() {
        UserLoyaltyProgram userLoyaltyProgram2 = new UserLoyaltyProgram(new UserLoyaltyProgramId(1, 1));
        List<UserLoyaltyProgram> userLoyaltyProgramList = List.of(
                userLoyaltyProgram,
                userLoyaltyProgram2
        );

        when(userLoyaltyProgramRepository.findAll()).thenReturn(userLoyaltyProgramList);

        userLoyaltyProgramService.getAll();

        verify(userLoyaltyProgramRepository, times(2)).findAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(userLoyaltyProgramRepository.findAll()).thenReturn(Collections.emptyList());

        userLoyaltyProgramService.getAll();

        verify(userLoyaltyProgramRepository, times(1)).findAll();
    }

    @Test
    void delete_Success() {
        when(userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(Optional.of(new User()));
        when(loyaltyProgramRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId())).thenReturn(Optional.of(new LoyaltyProgram()));

        userLoyaltyProgramService.delete(1,1);

        verify(userLoyaltyProgramRepository).deleteById(new UserLoyaltyProgramId(1,1));
    }

    @Test
    void delete_UserIdIsNull_ThrowNullPointerException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> userLoyaltyProgramService.delete(null,1));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"userId\" is null", exception.getMessage());
    }

    @Test
    void delete_UserIdNotFound_ThrowNoSuchElementException() {
        when(userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userLoyaltyProgramService.delete(1,1));
        assertEquals("Пользователь не найден с id: " + userLoyaltyProgram.getUserLoyaltyProgramId().getUserId(), exception.getMessage());
    }

    @Test
    void delete_LoyaltyProgramIdIsNull_ThrowNullPointerException() {
        when(userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(Optional.of(new User()));

        NullPointerException exception = assertThrows(NullPointerException.class, () -> userLoyaltyProgramService.delete(1,null));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"programId\" is null", exception.getMessage());
    }

    @Test
    void delete_BonusIdNotFound_ThrowNoSuchElementException() {
        when(userRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(Optional.of(new User()));
        when(loyaltyProgramRepository.findById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userLoyaltyProgramService.delete(1, 1));
        assertEquals("Программа лояльности не найдена с id: " + userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId(), exception.getMessage());
    }

    @Test
    void delete_IdsIsNegative_ThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userLoyaltyProgramService.delete(-1, -1));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

}
