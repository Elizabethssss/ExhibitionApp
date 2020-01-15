package model.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
    boolean add(T object);
    T getById(long id);
    boolean update(T object);
    boolean delete(T object);
    List<T> getAll() throws SQLException;
}
