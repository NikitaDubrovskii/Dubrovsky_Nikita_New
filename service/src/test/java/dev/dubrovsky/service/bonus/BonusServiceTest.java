package dev.dubrovsky.service.bonus;

import dev.dubrovsky.dao.bonus.BonusDao;
import dev.dubrovsky.dao.loyalty.program.LoyaltyProgramDao;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
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
class BonusServiceTest {

    @Mock
    private BonusDao bonusDao;

    @Mock
    private LoyaltyProgramDao loyaltyProgramDao;

    @InjectMocks
    private BonusService bonusService;

    private Bonus bonus;
    private Bonus updBonus;
    private LoyaltyProgram program;

    @BeforeEach
    void setUp() {
        bonusService = new BonusService(bonusDao, loyaltyProgramDao);

        program = new LoyaltyProgram();
        program.setId(1);

        bonus = new Bonus("test", "test", 10, program);
        bonus.setId(1);
        updBonus = new Bonus("upd", "upd", 100, program);
    }

    @Test
    void create_Success() {
        when(loyaltyProgramDao.getById(bonus.getProgram().getId())).thenReturn(new LoyaltyProgram());

        bonusService.create(bonus);

        verify(bonusDao).create(bonus);
    }

    @Test
    void create_BonusIsNull_ThrowIllegalArgumentException() {
        bonus = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bonusService.create(bonus));
        assertEquals("Бонус не может отсутствовать", exception.getMessage());
    }

    @Test
    void create_NameIsNull_ThrowIllegalArgumentException() {
        bonus.setName(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.create(bonus));
        assertEquals("Название должно быть", thrown.getMessage());
    }

    @Test
    void create_NameIsEmpty_ThrowIllegalArgumentException() {
        bonus.setName(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.create(bonus));
        assertEquals("Название должно быть", thrown.getMessage());
    }

    @Test
    void create_PointsIsNull_ThrowIllegalArgumentException() {
        bonus.setPoints(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.create(bonus));
        assertEquals("Количество очков должно быть", thrown.getMessage());
    }

    @Test
    void create_LoyaltyProgramIdIsNull_ThrowNullPointerException() {
        bonus.setProgram(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> bonusService.create(bonus));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.loyalty.program.LoyaltyProgram.getId()\" because the return value of \"dev.dubrovsky.model.bonus.Bonus.getProgram()\" is null", exception.getMessage());
    }

    @Test
    void create_LoyaltyProgramNotFound_ThrowNoSuchElementException() {
        when(loyaltyProgramDao.getById(bonus.getProgram().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> bonusService.create(bonus));
        assertEquals("Ничего не найдено с id: " + bonus.getProgram().getId(), exception.getMessage());
    }

    @Test
    void create_LoyaltyProgramIdIsNegative_ThrowIllegalArgumentException() {
        program.setId(-1);
        bonus.setProgram(program);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bonusService.create(bonus));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void create_LoyaltyProgramIdIsZero_ThrowIllegalArgumentException() {
        program.setId(0);
        bonus.setProgram(program);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bonusService.create(bonus));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(bonusDao.getById(id)).thenReturn(bonus);

        bonusService.getById(id);

        verify(bonusDao, times(2)).getById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> bonusService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());

    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(bonusDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> bonusService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        Bonus bonus2 = new Bonus("test2", "test2", 12, program);
        bonus2.setId(2);
        List<Bonus> bonuses = List.of(
                bonus,
                bonus2
        );

        when(bonusDao.getAll()).thenReturn(bonuses);

        bonusService.getAll();

        verify(bonusDao, times(2)).getAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(bonusDao.getAll()).thenReturn(Collections.emptyList());

        bonusService.getAll();

        verify(bonusDao, times(3)).getAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(loyaltyProgramDao.getById(updBonus.getProgram().getId())).thenReturn(new LoyaltyProgram());
        when(bonusDao.getById(id)).thenReturn(bonus);

        bonusService.update(updBonus, id);

        verify(bonusDao).update(updBonus);
    }

    @Test
    void update_BonusIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updBonus = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Бонус не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_NameIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updBonus.setName(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Название должно быть", thrown.getMessage());
    }

    @Test
    void update_NameIsEmpty_ThrowIllegalArgumentException() {
        Integer id = 1;
        updBonus.setName(" ");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Название должно быть", thrown.getMessage());
    }

    @Test
    void update_PointsIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updBonus.setPoints(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Количество очков должно быть", thrown.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(bonusDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void update_ProgramLoyaltyIdIsNull_ThrowNullPointerException() {
        Integer id = 1;
        updBonus.setProgram(null);
        when(bonusDao.getById(id)).thenReturn(new Bonus());

        NullPointerException exception = assertThrows(NullPointerException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Cannot invoke \"dev.dubrovsky.model.loyalty.program.LoyaltyProgram.getId()\" because the return value of \"dev.dubrovsky.model.bonus.Bonus.getProgram()\" is null", exception.getMessage());
    }

    @Test
    void update_ProgramLoyaltyIdIsZero_ThrowIllegalArgumentException() {
        Integer id = 1;
        program.setId(0);
        updBonus.setProgram(program);
        when(bonusDao.getById(id)).thenReturn(new Bonus());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void update_ProgramLoyaltyNotFound_ThrowNoSuchElementException() {
        Integer id = 1;
        when(bonusDao.getById(id)).thenReturn(new Bonus());
        when(loyaltyProgramDao.getById(bonus.getProgram().getId())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Ничего не найдено с id: " + updBonus.getProgram().getId(), exception.getMessage());
    }

    @Test
    void update_ProgramLoyaltyIdIsNegative_ThrowIllegalArgumentException() {
        Integer id = 1;
        program.setId(-1);
        updBonus.setProgram(program);
        when(bonusDao.getById(id)).thenReturn(new Bonus());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bonusService.update(updBonus, id));
        assertEquals("Id должен быть больше 0", exception.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(bonusDao.getById(id)).thenReturn(bonus);

        bonusService.delete(id);

        verify(bonusDao).delete(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> bonusService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bonusService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(bonusDao.getById(id)).thenReturn(null);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> bonusService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

}
