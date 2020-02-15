package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.Exposition;
import com.exhibition.app.entity.ExpositionEntity;

public class ExpositionMapper implements Mapper<ExpositionEntity, Exposition> {
    @Override
    public Exposition mapEntityToDomain(ExpositionEntity entity) {
        return Exposition.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withAbout(entity.getAbout())
                .withImage(entity.getImage())
                .withExhibitionId(entity.getExhibitionId())
                .build();
    }

    @Override
    public ExpositionEntity mapDomainToEntity(Exposition item) {
        return ExpositionEntity.builder()
                .withId(item.getId())
                .withName(item.getName())
                .withAbout(item.getAbout())
                .withImage(item.getImage())
                .withExhibitionId(item.getExhibitionId())
                .build();
    }
}
