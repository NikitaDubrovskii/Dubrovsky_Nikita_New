package dev.dubrovsky.service;

import java.util.List;

public interface ICommonService<T> {

    T create(T entity);

    T getById(Integer id);

    List<T> getAll();

    T update(T entity, Integer id);

    String delete(Integer id);

}
