package dev.dubrovsky.service.user;

import dev.dubrovsky.model.user.User;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private User updUser;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);

        user = new User("test", "test", "test");
        user.setId(1);
        updUser = new User("upd", "upd", "upd");
    }

    @Test
    void create_Success() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        userService.create(user);

        verify(userRepository).save(user);
    }

    @Test
    void create_UserIsNull_ThrowIllegalArgumentException() {
        user = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Пользователь не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_EmailIsNull_ThrowIllegalArgumentException() {
        user.setEmail(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Почта не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_EmailIsEmpty_ThrowIllegalArgumentException() {
        user.setEmail(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Почта не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_PasswordIsNull_ThrowIllegalArgumentException() {
        user.setPassword(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Пароль не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_PasswordIsEmpty_ThrowIllegalArgumentException() {
        user.setPassword(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Пароль не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_UsernameIsNull_ThrowIllegalArgumentException() {
        user.setUsername(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Имя не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_UsernameIsEmpty_ThrowIllegalArgumentException() {
        user.setUsername(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Имя не может отсутствовать", thrown.getMessage());
    }

    @Test
    void create_NonUniqueUsername_ThrowIllegalArgumentException() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(new User());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Пользователь уже существует с именем: " + user.getUsername(), exception.getMessage());
    }

    @Test
    void create_NonUniqueEmail_ThrowIllegalArgumentException() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(new User());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.create(user));
        assertEquals("Пользователь уже существует с почтой: " + user.getEmail(), exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.getById(id);

        verify(userRepository, times(2)).findById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> userService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> userService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        User user2 = new User("test2", "test2", "test2");
        user2.setId(2);
        List<User> userList = List.of(
                user,
                user2
        );

        when(userRepository.findAll()).thenReturn(userList);

        userService.getAll();

        verify(userRepository, times(2)).findAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        userService.getAll();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.findByUsername(updUser.getUsername())).thenReturn(null);
        when(userRepository.findByEmail(updUser.getEmail())).thenReturn(null);

        userService.update(updUser, id);

        verify(userRepository).save(updUser);
    }

    @Test
    void update_UserIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updUser = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Пользователь не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_EmailIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updUser.setEmail(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Почта не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_EmailIsEmpty_ThrowIllegalArgumentException() {
        Integer id = 1;
        updUser.setEmail(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Почта не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_PasswordIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updUser.setPassword(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Пароль не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_PasswordIsEmpty_ThrowIllegalArgumentException() {
        Integer id = 1;
        updUser.setPassword(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Пароль не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_UsernameIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updUser.setUsername(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Имя не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_UsernameIsEmpty_ThrowIllegalArgumentException() {
        Integer id = 1;
        updUser.setUsername(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Имя не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> userService.update(updUser, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> userService.update(updUser, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void update_NonUniqueUsername_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.findByUsername(updUser.getUsername())).thenReturn(new User());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Пользователь уже существует с именем: " + updUser.getUsername(), exception.getMessage());
    }

    @Test
    void update_NonUniqueEmail_ThrowIllegalArgumentException() {
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(updUser.getEmail())).thenReturn(new User());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.update(updUser, id));
        assertEquals("Пользователь уже существует с почтой: " + updUser.getEmail(), exception.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.delete(id);

        verify(userRepository).deleteById(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> userService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> userService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> userService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

}
