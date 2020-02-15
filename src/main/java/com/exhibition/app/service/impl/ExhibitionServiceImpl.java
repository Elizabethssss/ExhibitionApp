package com.exhibition.app.service.impl;

import com.exhibition.app.dao.ExhibitionDao;
import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.entity.ExhibitionEntity;
import com.exhibition.app.service.ExhibitionService;
import com.exhibition.app.service.mapper.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExhibitionServiceImpl implements ExhibitionService {
    private final ExhibitionDao exhibitionDao;
    private final Mapper<ExhibitionEntity, Exhibition> exhibitionMapper;
    private final String[] months = new String[] {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "November", "December"};

    public ExhibitionServiceImpl(ExhibitionDao exhibitionDao,
                                 Mapper<ExhibitionEntity, Exhibition> exhibitionMapper) {
        this.exhibitionDao = exhibitionDao;
        this.exhibitionMapper = exhibitionMapper;
    }

    @Override
    public Optional<Exhibition> getExhibitionById(long id) {
        return exhibitionDao.findById(id).map(exhibitionMapper::mapEntityToDomain);
    }

    @Override
    public Map<String, List<Exhibition>> getMonthExhibitionsMap() {
        Map<String, List<Exhibition>> result = new LinkedHashMap<>();
        for (int i = 1; i <= months.length; i++) {
            List<Exhibition> temp = exhibitionDao.findByDateLike("%-%" + i + "-%").stream()
                    .map(exhibitionMapper::mapEntityToDomain).collect(Collectors.toList());
            result.put(months[i-1], temp);
        }
        return result;
    }
}
