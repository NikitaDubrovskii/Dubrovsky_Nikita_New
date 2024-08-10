package dev.dubrovsky.service;

public interface ICommonService<T> {

    void create(T entity);

    T getById(Integer id);

    void getAll();

    void update(T entity, Integer id);

    void delete(Integer id);

}
