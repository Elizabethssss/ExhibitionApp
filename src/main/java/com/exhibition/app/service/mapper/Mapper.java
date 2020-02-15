package com.exhibition.app.service.mapper;

public interface Mapper<E, D> {
    D mapEntityToDomain(E entity);
    E mapDomainToEntity(D item);
}
