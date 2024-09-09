package dev.dubrovsky.service.analytics;

import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.model.user.User;
import dev.dubrovsky.repository.analytics.AnalyticsRepository;
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
class AnalyticsServiceTest {

    @Mock
    private AnalyticsRepository analyticsRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AnalyticsService analyticsService;

    private Analytics analytics;
    private Analytics updAnalytics;
    private User user;

    @BeforeEach
    void setUp() {
        analyticsService = new AnalyticsService(analyticsRepository, userRepository);

        user = new User();
        user.setId(1);

        analytics = new Analytics("test", user);
        analytics.setId(1);
        updAnalytics = new Analytics("upd", user);
    }

    @Test
    void create_Success() {
        when(userRepository.findById(analytics.getUser().getId())).thenReturn(Optional.of(new User()));

        analyticsService.create(analytics);

        verify(analyticsRepository).save(analytics);
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
        when(userRepository.findById(analytics.getUser().getId())).thenReturn(Optional.empty());

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
        when(analyticsRepository.findById(id)).thenReturn(Optional.of(analytics));

        analyticsService.getById(id);

        verify(analyticsRepository, times(2)).findById(id);
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
        when(analyticsRepository.findById(id)).thenReturn(Optional.empty());

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

        when(analyticsRepository.findAll()).thenReturn(analyticsList);

        analyticsService.getAll();

        verify(analyticsRepository, times(2)).findAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(analyticsRepository.findAll()).thenReturn(Collections.emptyList());

        analyticsService.getAll();

        verify(analyticsRepository, times(1)).findAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(userRepository.findById(updAnalytics.getUser().getId())).thenReturn(Optional.of(new User()));
        when(analyticsRepository.findById(id)).thenReturn(Optional.of(analytics));

        analyticsService.update(updAnalytics, id);

        verify(analyticsRepository).save(updAnalytics);
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
        when(analyticsRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void update_UserIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        updAnalytics.setUser(null);
        when(analyticsRepository.findById(id)).thenReturn(Optional.of(new Analytics()));

        NullPointerException exception = assertThrows(NullPointerException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.user.User.getId()\" because the return value of \"dev.dubrovsky.model.analytics.Analytics.getUser()\" is null", exception.getMessage());
    }

    @Test
    void update_UserNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(analyticsRepository.findById(id)).thenReturn(Optional.of(new Analytics()));
        when(userRepository.findById(analytics.getUser().getId())).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Ничего не найдено с id: " + updAnalytics.getUser().getId(), exception.getMessage());
    }

    @Test
    void update_UserIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        user.setId(0);
        updAnalytics.setUser(user);
        when(analyticsRepository.findById(id)).thenReturn(Optional.of(new Analytics()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_UserIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        user.setId(-1);
        updAnalytics.setUser(user);
        when(analyticsRepository.findById(id)).thenReturn(Optional.of(new Analytics()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> analyticsService.update(updAnalytics, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());

    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(analyticsRepository.findById(id)).thenReturn(Optional.of(analytics));

        analyticsService.delete(id);

        verify(analyticsRepository).deleteById(id);
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
