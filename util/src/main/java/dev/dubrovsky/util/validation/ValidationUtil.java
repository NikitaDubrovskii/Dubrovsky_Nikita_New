package dev.dubrovsky.util.validation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    /*public static <T> void checkId(Integer id, AbstractDao<T> dao) {
        check(id, dao);
    }

    public static <T> void checkEntityPresent(Integer id, AbstractDao<T> dao) {
        check(id, dao);
    }*/

    /*private static <T> void check(Integer id, AbstractDao<T> dao) {
        if (id > 0) {
            Optional
                    .ofNullable(dao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }*/

    public static <T> void checkId(Integer id, JpaRepository<T, Integer> repository) {
        check(id, repository);
    }

    public static <T> void checkEntityPresent(Integer id, JpaRepository<T, Integer> repository) {
        check(id, repository);
    }

    private static <T> void check(Integer id, JpaRepository<T, Integer> repository) {
        if (id > 0) {
            repository
                    .findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
