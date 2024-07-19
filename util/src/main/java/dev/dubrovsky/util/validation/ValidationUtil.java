package dev.dubrovsky.util.validation;

import dev.dubrovsky.dao.AbstractDao;

import java.util.NoSuchElementException;
import java.util.Optional;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> void checkId(Integer id, AbstractDao<T> dao) {
        check(id, dao);
    }

    public static <T> void checkEntityPresent(Integer id, AbstractDao<T> dao) {
        check(id, dao);
    }

    private static <T> void check(Integer id, AbstractDao<T> dao) {
        if (id > 0) {
            Optional
                    .ofNullable(dao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
