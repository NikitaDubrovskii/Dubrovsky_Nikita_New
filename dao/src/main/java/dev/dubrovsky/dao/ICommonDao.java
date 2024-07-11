package dev.dubrovsky.dao;

import java.util.List;

public interface ICommonDao<T> {

    void create(T entity);

    List<T> getAll();

    void update(T entity);

    void delete(Integer id);

}
