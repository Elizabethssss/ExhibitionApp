package com.exhibition.app.dao;

import java.util.List;

public interface PageableDao<T> extends GenericDao<T> {
    List<T> findAll(Page page);
}
