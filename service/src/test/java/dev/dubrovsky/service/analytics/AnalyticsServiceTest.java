package dev.dubrovsky.service.analytics;

import dev.dubrovsky.dao.analytics.AnalyticsDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.analytics.Analytics;
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
class AnalyticsServiceTest {

    @Mock
    private AnalyticsDao analyticsDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private AnalyticsService analyticsService;

    private Analytics analytics;
    private Analytics updAnalytics;
    private User user;

    @BeforeEach
    void setUp() {
        analyticsService = new AnalyticsService(analyticsDao, userDao);

        user = new User();
        user.setId(1);

        analytics = new Analytics("test", user);
        analytics.setId(1);
        updAnalytics = new Analytics("upd", user);
    }

    @Test
    void create_Success() {
        when(userDao.getById(analytics.getUser().getId())).thenReturn(new User());

        analyticsService.create(analytics);

        verify(analyticsDao).create(analytics);
    }

    @Test
    void create_AnalyticsIsNull_ThrowIllegalArgumentException() {
        analytics = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> analyticsService.create(analytics));
        assertEquals("Аналитика не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_UserIdIsNull_ThrowNullPointerException() {
        analytics.setUser(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> analyticsService.create(analytics));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.user.User.getId()\" because the return value of \"dev.dubrovsky.model.analytics.Analytics.getUser()\" is null", exception.getMessage());
    }

    @Test
    void create_UserNotFound_ThrowNoSuchElementException() {
        when(userDao.getById(analytics.getUser().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> analyticsService.create(analytics));
        assertEquals("Ничего не найдено с id: " + analytics.getUser().getId(), exception.getMessage());
    }

    @Test
    void create_UserIdIsNegative_ThrowIllegalArgumentException() {
        user.setId(-1);
        analytics.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> analyticsService.create(analytics));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_UserIdIsZero_ThrowIllegalArgumentException() {
        user.setId(0);
        analytics.setUser(user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> analyticsService.create(analytics));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(analyticsDao.getById(id)).thenReturn(analytics);

        analyticsService.getById(id);

        verify(analyticsDao, times(2)).getById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> analyticsService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> analyticsService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> analyticsService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(analyticsDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> analyticsService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        Analytics analytics2 = new Analytics("Test2", user);
        analytics2.setId(2);
        List<Analytics> analyticsList = List.of(
                analytics,
                analytics2
        );

        when(analyticsDao.getAll()).thenReturn(analyticsList);

        analyticsService.getAll();

        verify(analyticsDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(analyticsDao.getAll()).thenReturn(Collections.emptyList());

        analyticsService.getAll();

        verify(analyticsDao, times(3)).getAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(userDao.getById(updAnalytics.getUser().getId())).thenReturn(new User());
        when(analyticsDao.getById(id)).thenReturn(analytics);

        analyticsService.update(updAnalytics, id);

        verify(analyticsDao).update(updAnalytics);
    }

    @Test
    void update_AnalyticsIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updAnalytics = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Аналитика не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(analyticsDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void update_UserIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        updAnalytics.setUser(null);
        when(analyticsDao.getById(id)).thenReturn(new Analytics());

        NullPointerException exception = assertThrows(NullPointerException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.user.User.getId()\" because the return value of \"dev.dubrovsky.model.analytics.Analytics.getUser()\" is null", exception.getMessage());
    }

    @Test
    void update_UserNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(analyticsDao.getById(id)).thenReturn(new Analytics());
        when(userDao.getById(analytics.getUser().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Ничего не найдено с id: " + updAnalytics.getUser().getId(), exception.getMessage());
    }

    @Test
    void update_UserIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        user.setId(0);
        updAnalytics.setUser(user);
        when(analyticsDao.getById(id)).thenReturn(new Analytics());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_UserIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        user.setId(-1);
        updAnalytics.setUser(user);
        when(analyticsDao.getById(id)).thenReturn(new Analytics());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());

    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(analyticsDao.getById(id)).thenReturn(analytics);

        analyticsService.delete(id);

        verify(analyticsDao).delete(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> analyticsService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> analyticsService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> analyticsService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> analyticsService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

}
