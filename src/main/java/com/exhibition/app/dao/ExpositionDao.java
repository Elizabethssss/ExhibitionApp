package com.exhibition.app.dao;

import com.exhibition.app.entity.ExpositionEntity;

import java.util.Optional;

public interface ExpositionDao extends PageableDao<ExpositionEntity> {
    Optional<ExpositionEntity> findByExhibitionId(long exhibitionId);
}
