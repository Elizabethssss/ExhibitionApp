package com.exhibition.app.service.impl;

import com.exhibition.app.dao.ExpositionDao;
import com.exhibition.app.dao.Page;
import com.exhibition.app.domain.Exposition;
import com.exhibition.app.entity.ExpositionEntity;
import com.exhibition.app.service.ExpositionService;
import com.exhibition.app.service.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class ExpositionServiceImpl implements ExpositionService {
    final ExpositionDao expositionDao;
    final Mapper<ExpositionEntity, Exposition> expositionMapper;

    public ExpositionServiceImpl(ExpositionDao expositionDao, Mapper<ExpositionEntity, Exposition> expositionMapper) {
        this.expositionDao = expositionDao;
        this.expositionMapper = expositionMapper;
    }

    @Override
    public List<Exposition> getExpositionsByExhibId(long id, Page page) {
        return expositionDao.findAll(id, page).stream()
                .map(expositionMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public int count(Long id) {
        return expositionDao.count(id);
    }
}
