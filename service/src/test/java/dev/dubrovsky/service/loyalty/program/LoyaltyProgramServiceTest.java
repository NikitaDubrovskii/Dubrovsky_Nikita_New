package dev.dubrovsky.service.loyalty.program;

import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.repository.loyalty.program.LoyaltyProgramRepository;
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
class LoyaltyProgramServiceTest {

    @Mock
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @InjectMocks
    private LoyaltyProgramService loyaltyProgramService;

    private LoyaltyProgram loyaltyProgram;
    private LoyaltyProgram updloyaltyProgram;

    @BeforeEach
    void setUp() {
        loyaltyProgramService = new LoyaltyProgramService(loyaltyProgramRepository);

        loyaltyProgram = new LoyaltyProgram("test", "test");
        loyaltyProgram.setId(1);
        updloyaltyProgram = new LoyaltyProgram("upd", "upd");
    }

    @Test
    void create_Success() {
        loyaltyProgramService.create(loyaltyProgram);

        verify(loyaltyProgramRepository).save(loyaltyProgram);
    }

    @Test
    void create_LoyaltyProgramIsNull_ThrowIllegalArgumentException() {
        loyaltyProgram = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.create(loyaltyProgram));
        assertEquals("Программа лояльности не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_NameIsNull_ThrowIllegalArgumentException() {
        loyaltyProgram.setName(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.create(loyaltyProgram));
        assertEquals("Название должно быть", exception.getMessage());
    }

    @Test
    void create_NameIsEmpty_ThrowIllegalArgumentException() {
        loyaltyProgram.setName(" ");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.create(loyaltyProgram));
        assertEquals("Название должно быть", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(loyaltyProgramRepository.findById(id)).thenReturn(Optional.of(loyaltyProgram));

        loyaltyProgramService.getById(id);

        verify(loyaltyProgramRepository, times(2)).findById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> loyaltyProgramService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());

    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(loyaltyProgramRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> loyaltyProgramService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        LoyaltyProgram loyaltyProgram2 = new LoyaltyProgram("upd", "upd");
        loyaltyProgram2.setId(2);
        List<LoyaltyProgram> loyaltyProgramList = List.of(
                loyaltyProgram,
                loyaltyProgram2
        );

        when(loyaltyProgramRepository.findAll()).thenReturn(loyaltyProgramList);

        loyaltyProgramService.getAll();

        verify(loyaltyProgramRepository, times(2)).findAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(loyaltyProgramRepository.findAll()).thenReturn(Collections.emptyList());

        loyaltyProgramService.getAll();

        verify(loyaltyProgramRepository, times(1)).findAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(loyaltyProgramRepository.findById(id)).thenReturn(Optional.of(loyaltyProgram));

        loyaltyProgramService.update(updloyaltyProgram, id);

        verify(loyaltyProgramRepository).save(updloyaltyProgram);
    }

    @Test
    void update_LoyaltyProgramIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updloyaltyProgram = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.update(updloyaltyProgram, id));
        assertEquals("Программа лояльности не может отсутствовать", exception.getMessage());
    }

    @Test
    void update_NameIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updloyaltyProgram.setName(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.update(updloyaltyProgram, id));
        assertEquals("Название должно быть", exception.getMessage());
    }

    @Test
    void update_NameIsEmpty_ThrowIllegalArgumentException() {
        Integer id = 1;
        updloyaltyProgram.setName(" ");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.update(updloyaltyProgram, id));
        assertEquals("Название должно быть", exception.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> loyaltyProgramService.update(updloyaltyProgram, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.update(updloyaltyProgram, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.update(updloyaltyProgram, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(loyaltyProgramRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> loyaltyProgramService.update(updloyaltyProgram, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(loyaltyProgramRepository.findById(id)).thenReturn(Optional.of(loyaltyProgram));

        loyaltyProgramService.delete(id);

        verify(loyaltyProgramRepository).deleteById(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> loyaltyProgramService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> loyaltyProgramService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(loyaltyProgramRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> loyaltyProgramService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

}
