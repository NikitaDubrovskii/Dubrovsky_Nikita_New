package dev.dubrovsky.service;

public interface ICommonService<T> {

    void create(T entity);

    void getAll();

    void update(T entity);

    void delete(Integer id);

}
