package com.exhibition.app.service;

import com.exhibition.app.dao.Page;
import com.exhibition.app.domain.Exposition;

import java.util.List;

public interface ExpositionService {
    List<Exposition> getExpositionsByExhibId(long id, Page page);
    int count(Long id);
}
