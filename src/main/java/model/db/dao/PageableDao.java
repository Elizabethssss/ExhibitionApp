package model.db.dao;

import java.sql.Connection;
import java.util.List;

public interface PageableDao<T> extends GenericDao<T> {
    List<T> findAll(long id, Page page, Connection connection);
}
