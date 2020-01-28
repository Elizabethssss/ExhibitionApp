package model.db.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    boolean add(T object);
    Optional<T> getById(long id);
    boolean update(T object);
    boolean delete(T object);
    List<T> getAll();
}
