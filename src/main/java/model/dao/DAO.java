package model.dao;

import java.util.List;

public interface DAO<T> {
    void insert(T o);
    void update(T o);
    void delete(T o);
    T getById(String id);
    List<T> getAll();
}
