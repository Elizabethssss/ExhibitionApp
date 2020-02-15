package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.Role;
import com.exhibition.app.entity.RoleEntity;

public class RoleMapper implements Mapper<RoleEntity, Role> {
    @Override
    public Role mapEntityToDomain(RoleEntity entity) {
        return Role.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .build();
    }

    @Override
    public RoleEntity mapDomainToEntity(Role item) {
        return RoleEntity.builder()
                .withId(item.getId())
                .withName(item.getName())
                .withDescription(item.getDescription())
                .build();
    }
}
