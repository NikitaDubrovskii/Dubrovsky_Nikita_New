package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dao.bonus.BonusDao;
import dev.dubrovsky.dao.bonus.UserBonusDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.bonus.UserBonus;
import dev.dubrovsky.model.bonus.UserBonusId;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserBonusServiceTest {

    @Mock
    private UserBonusDao userBonusDao;

    @Mock
    private UserDao userDao;

    @Mock
    private BonusDao bonusDao;

    @InjectMocks
    private UserBonusService userBonusService;

    private UserBonus userBonus;

    @BeforeEach
    void setUp() {
        userBonusService = new UserBonusService(userBonusDao, userDao, bonusDao);

        userBonus = new UserBonus(new UserBonusId(1, 1));
    }

    @Test
    void create_Success() {
        when(userDao.getById(userBonus.getUserBonusId().getUserId())).thenReturn(new User());
        when(bonusDao.getById(userBonus.getUserBonusId().getBonusId())).thenReturn(new Bonus());

        userBonusService.create(userBonus);

        verify(userBonusDao).create(userBonus);
    }

    @Test
    void create_UserBonusIsNull_ThrowIllegalArgumentException() {
        userBonus = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userBonusService.create(userBonus));
        assertEquals("Бонус пользователя не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_UserIdIsNull_ThrowNullPointerException() {
        userBonus.getUserBonusId().setUserId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> userBonusService.create(userBonus));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"userId\" is null", exception.getMessage());
    }

    @Test
    void create_UserIdNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(userBonus.getUserBonusId().getUserId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userBonusService.create(userBonus));
        assertEquals("Пользователь не найден с id: " + userBonus.getUserBonusId().getUserId(), exception.getMessage());
    }

    @Test
    void create_BonusIdIsNull_ThrowNullPointerException() {
        when(userDao.getById(userBonus.getUserBonusId().getUserId())).thenReturn(new User());
        userBonus.getUserBonusId().setBonusId(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> userBonusService.create(userBonus));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"bonusId\" is null", exception.getMessage());
    }

    @Test
    void create_BonusIdNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(userBonus.getUserBonusId().getUserId())).thenReturn(new User());
        when(bonusDao.getById(userBonus.getUserBonusId().getBonusId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userBonusService.create(userBonus));
        assertEquals("Бонус не найден с id: " + userBonus.getUserBonusId().getBonusId(), exception.getMessage());
    }

    @Test
    void create_IdsIsNegative_ThrowIllegalArgumentException() {
        userBonus.getUserBonusId().setUserId(-1);
        userBonus.getUserBonusId().setBonusId(-1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userBonusService.create(userBonus));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getAll_Success() {
        UserBonus userBonus2 = new UserBonus(new UserBonusId(1, 1));
        List<UserBonus> userBonusList = List.of(
                userBonus,
                userBonus2
        );

        when(userBonusDao.getAll()).thenReturn(userBonusList);

        userBonusService.getAll();

        verify(userBonusDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(userBonusDao.getAll()).thenReturn(Collections.emptyList());

        userBonusService.getAll();

        verify(userBonusDao, times(3)).getAll();
    }

    @Test
    void delete_Success() {
        when(userDao.getById(userBonus.getUserBonusId().getUserId())).thenReturn(new User());
        when(bonusDao.getById(userBonus.getUserBonusId().getBonusId())).thenReturn(new Bonus());

        userBonusService.delete(1,1);

        verify(userBonusDao).delete(1,1);
    }

    @Test
    void delete_UserIdIsNull_ThrowNullPointerException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> userBonusService.delete(null,1));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"userId\" is null", exception.getMessage());
    }

    @Test
    void delete_UserIdNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(userBonus.getUserBonusId().getUserId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userBonusService.delete(1,1));
        assertEquals("Пользователь не найден с id: " + userBonus.getUserBonusId().getUserId(), exception.getMessage());
    }

    @Test
    void delete_BonusIdIsNull_ThrowNullPointerException() {
        when(userDao.getById(userBonus.getUserBonusId().getUserId())).thenReturn(new User());

        NullPointerException exception = assertThrows(NullPointerException.class, () -> userBonusService.delete(1,null));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"bonusId\" is null", exception.getMessage());
    }

    @Test
    void delete_BonusIdNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(userBonus.getUserBonusId().getUserId())).thenReturn(new User());
        when(bonusDao.getById(userBonus.getUserBonusId().getBonusId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userBonusService.delete(1, 1));
        assertEquals("Бонус не найден с id: " + userBonus.getUserBonusId().getBonusId(), exception.getMessage());
    }

    @Test
    void delete_IdsIsNegative_ThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userBonusService.delete(-1, -1));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

}
