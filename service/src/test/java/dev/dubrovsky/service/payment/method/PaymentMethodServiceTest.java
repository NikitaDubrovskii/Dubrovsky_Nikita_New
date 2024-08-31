package dev.dubrovsky.service.payment.method;

import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.repository.payment.method.PaymentMethodRepository;
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
class PaymentMethodServiceTest {

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @InjectMocks
    private PaymentMethodService paymentMethodService;

    private PaymentMethod paymentMethod;
    private PaymentMethod updPaymentMethod;

    @BeforeEach
    void setUp() {
        paymentMethodService = new PaymentMethodService(paymentMethodRepository);

        paymentMethod = new PaymentMethod("test");
        paymentMethod.setId(1);
        updPaymentMethod = new PaymentMethod("upd");
    }

    @Test
    void create_Success() {
        paymentMethodService.create(paymentMethod);

        verify(paymentMethodRepository).save(paymentMethod);
    }

    @Test
    void create_PaymentMethodIsNull_ThrowIllegalArgumentException() {
        paymentMethod = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> paymentMethodService.create(paymentMethod));
        assertEquals("Способ оплаты не может отсутствовать", exception.getMessage());
    }

    @Test
    void getById_Success() {
        Integer id = 1;
        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(paymentMethod));

        paymentMethodService.getById(id);

        verify(paymentMethodRepository, times(2)).findById(id);
    }

    @Test
    void getById_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> paymentMethodService.getById(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void getById_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> paymentMethodService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> paymentMethodService.getById(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void getById_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(paymentMethodRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> paymentMethodService.getById(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void getAll_Success() {
        PaymentMethod paymentMethod2 = new PaymentMethod("test2");
        paymentMethod2.setId(2);
        List<PaymentMethod> paymentMethodList = List.of(
                paymentMethod,
                paymentMethod2
        );

        when(paymentMethodRepository.findAll()).thenReturn(paymentMethodList);

        paymentMethodService.getAll();

        verify(paymentMethodRepository, times(2)).findAll();
    }

    @Test
    void getAll_ListIsEmpty() {
        when(paymentMethodRepository.findAll()).thenReturn(Collections.emptyList());

        paymentMethodService.getAll();

        verify(paymentMethodRepository, times(1)).findAll();
    }

    @Test
    void update_Success() {
        Integer id = 1;
        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(paymentMethod));

        paymentMethodService.update(updPaymentMethod, id);

        verify(paymentMethodRepository).save(updPaymentMethod);
    }

    @Test
    void update_PaymentMethodIsNull_ThrowIllegalArgumentException() {
        Integer id = 1;
        updPaymentMethod = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> paymentMethodService.update(updPaymentMethod, id));
        assertEquals("Способ оплаты не может отсутствовать", thrown.getMessage());
    }

    @Test
    void update_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> paymentMethodService.update(updPaymentMethod, id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void update_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> paymentMethodService.update(updPaymentMethod, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> paymentMethodService.update(updPaymentMethod, id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void update_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 45;
        when(paymentMethodRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> paymentMethodService.update(updPaymentMethod, id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

    @Test
    void delete_Success() {
        Integer id = 1;

        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(paymentMethod));

        paymentMethodService.delete(id);

        verify(paymentMethodRepository).deleteById(id);
    }

    @Test
    void delete_IdIsNull_ThrowNullPointerException() {
        Integer id = null;

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> paymentMethodService.delete(id));
        assertEquals("Cannot invoke \"java.lang.Integer.intValue()\" because \"id\" is null", thrown.getMessage());
    }

    @Test
    void delete_IdIsZero_ThrowIllegalArgumentException() {
        Integer id = 0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> paymentMethodService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdIsNegative_ThrowIllegalArgumentException() {
        Integer id = -44;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> paymentMethodService.delete(id));
        assertEquals("Id должен быть больше 0", thrown.getMessage());
    }

    @Test
    void delete_IdNotFound_ThrowNoSuchElementException() {
        Integer id = 44;
        when(paymentMethodRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> paymentMethodService.delete(id));
        assertEquals("Ничего не найдено с id: " + id, thrown.getMessage());
    }

}
