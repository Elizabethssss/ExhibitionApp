package com.exhibition.app.service;

import com.exhibition.app.domain.Exhibition;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExhibitionService {
    Optional<Exhibition> getExhibitionById(long id);
    Map<String, List<Exhibition>> getMonthExhibitionsMap();
}
