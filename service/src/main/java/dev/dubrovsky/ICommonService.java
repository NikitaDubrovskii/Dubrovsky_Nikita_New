package dev.dubrovsky;

public interface ICommonService<T> {

    void create(T entity);

    void getAll();

    void update(T entity);

    void delete(Integer id);

}
