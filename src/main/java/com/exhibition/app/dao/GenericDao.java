package com.exhibition.app.dao;

import java.util.Optional;

public interface GenericDao<T> {
    boolean save(T object);
    Optional<T> findById(long id);
    boolean update(T object);
    boolean delete(long id);
}
