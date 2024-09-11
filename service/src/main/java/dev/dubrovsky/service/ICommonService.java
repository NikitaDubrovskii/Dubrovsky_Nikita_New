package dev.dubrovsky.service;

import java.util.List;

public interface ICommonService<R, N, U> {

    void create(N entity);

    R getById(Integer id);

    List<R> getAll();

    void update(U entity, Integer id);

    void delete(Integer id);

}
