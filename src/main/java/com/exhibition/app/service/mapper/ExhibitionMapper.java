package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.entity.ExhibitionEntity;

public class ExhibitionMapper implements Mapper<ExhibitionEntity, Exhibition> {
    @Override
    public Exhibition mapEntityToDomain(ExhibitionEntity entity) {
        return Exhibition.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDateFrom(entity.getDateFrom())
                .withDateTo(entity.getDateTo())
                .withTheme(entity.getTheme())
                .withAbout(entity.getAbout())
                .withLongAbout(entity.getLongAbout())
                .withPrice(entity.getPrice())
                .withImage(entity.getImage())
                .build();
    }

    @Override
    public ExhibitionEntity mapDomainToEntity(Exhibition item) {
        return ExhibitionEntity.builder()
                .withId(item.getId())
                .withName(item.getName())
                .withDateFrom(item.getDateFrom())
                .withDateTo(item.getDateTo())
                .withTheme(item.getTheme())
                .withAbout(item.getAbout())
                .withLongAbout(item.getLongAbout())
                .withPrice(item.getPrice())
                .withImage(item.getImage())
                .build();
    }
}
