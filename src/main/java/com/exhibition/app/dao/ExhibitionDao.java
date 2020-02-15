package com.exhibition.app.dao;

import com.exhibition.app.entity.ExhibitionEntity;

import java.util.List;

public interface ExhibitionDao extends GenericDao<ExhibitionEntity> {
    List<ExhibitionEntity> findByDateLike(String date);
}
