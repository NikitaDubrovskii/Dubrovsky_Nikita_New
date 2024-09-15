package dev.dubrovsky.util.validation;

import dev.dubrovsky.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> void checkId(Integer id, JpaRepository<T, Integer> repository) {
        check(id, repository);
    }

    public static <T> void checkEntityPresent(Integer id, JpaRepository<T, Integer> repository) {
        check(id, repository);
    }

    private static <T> void check(Integer id, JpaRepository<T, Integer> repository) {
        if (id >= 0) {
            repository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
