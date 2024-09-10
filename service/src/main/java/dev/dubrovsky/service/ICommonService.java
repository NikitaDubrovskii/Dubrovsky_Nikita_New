package dev.dubrovsky.service;

import java.util.List;

public interface ICommonService<T, N, U> {

    T create(N entity);

    T getById(Integer id);

    List<T> getAll();

    T update(U entity, Integer id);

    String delete(Integer id);

}
