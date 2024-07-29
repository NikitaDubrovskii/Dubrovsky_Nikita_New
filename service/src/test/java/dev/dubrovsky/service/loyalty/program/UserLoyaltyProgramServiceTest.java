package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.dao.loyalty.program.LoyaltyProgramDao;
import dev.dubrovsky.dao.loyalty.program.UserLoyaltyProgramDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgramId;
import dev.dubrovsky.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserLoyaltyProgramServiceTest {

    @Mock
    private UserLoyaltyProgramDao userLoyaltyProgramDao;

    @Mock
    private UserDao userDao;

    @Mock
    private LoyaltyProgramDao loyaltyProgramDao;

    @InjectMocks
    private UserLoyaltyProgramService userLoyaltyProgramService;

    private UserLoyaltyProgram userLoyaltyProgram;

    @BeforeEach
    void setUp() {
        userLoyaltyProgramService = new UserLoyaltyProgramService(userLoyaltyProgramDao, userDao, loyaltyProgramDao);

        userLoyaltyProgram = new UserLoyaltyProgram(new UserLoyaltyProgramId(1, 1));
    }

    @Test
    void create_Success() {
        when(userDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(new User());
        when(loyaltyProgramDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId())).thenReturn(new LoyaltyProgram());

        userLoyaltyProgramService.create(userLoyaltyProgram);

        verify(userLoyaltyProgramDao).create(userLoyaltyProgram);
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
        when(userDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userLoyaltyProgramService.create(userLoyaltyProgram));
        assertEquals("Пользователь не найден с id: " + userLoyaltyProgram.getUserLoyaltyProgramId().getUserId(), exception.getMessage());
    }

    @Test
    void create_LoyaltyProgramIdIsNull_ThrowNullPointerException() {
        when(userDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(new User());
        userLoyaltyProgram.getUserLoyaltyProgramId().setProgramId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> userLoyaltyProgramService.create(userLoyaltyProgram));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"programId\" is null", exception.getMessage());
    }

    @Test
    void create_LoyaltyProgramIdNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(new User());
        when(loyaltyProgramDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId())).thenReturn(null);

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

        when(userLoyaltyProgramDao.getAll()).thenReturn(userLoyaltyProgramList);

        userLoyaltyProgramService.getAll();

        verify(userLoyaltyProgramDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(userLoyaltyProgramDao.getAll()).thenReturn(Collections.emptyList());

        userLoyaltyProgramService.getAll();

        verify(userLoyaltyProgramDao, times(3)).getAll();
    }

    @Test
    void delete_Success() {
        when(userDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(new User());
        when(loyaltyProgramDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId())).thenReturn(new LoyaltyProgram());

        userLoyaltyProgramService.delete(1,1);

        verify(userLoyaltyProgramDao).delete(1,1);
    }

    @Test
    void delete_UserIdIsNull_ThrowNullPointerException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> userLoyaltyProgramService.delete(null,1));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"userId\" is null", exception.getMessage());
    }

    @Test
    void delete_UserIdNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userLoyaltyProgramService.delete(1,1));
        assertEquals("Пользователь не найден с id: " + userLoyaltyProgram.getUserLoyaltyProgramId().getUserId(), exception.getMessage());
    }

    @Test
    void delete_LoyaltyProgramIdIsNull_ThrowNullPointerException() {
        when(userDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(new User());

        NullPointerException exception = assertThrows(NullPointerException.class, () -> userLoyaltyProgramService.delete(1,null));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"programId\" is null", exception.getMessage());
    }

    @Test
    void delete_BonusIdNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getUserId())).thenReturn(new User());
        when(loyaltyProgramDao.getById(userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userLoyaltyProgramService.delete(1, 1));
        assertEquals("Программа лояльности не найдена с id: " + userLoyaltyProgram.getUserLoyaltyProgramId().getProgramId(), exception.getMessage());
    }

    @Test
    void delete_IdsIsNegative_ThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userLoyaltyProgramService.delete(-1, -1));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

}
