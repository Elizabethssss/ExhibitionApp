package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.Ticket;
import com.exhibition.app.entity.TicketEntity;

import java.util.Optional;

public class TicketMapper implements Mapper<TicketEntity, Ticket>{

    @Override
    public Ticket mapEntityToDomain(TicketEntity entity) {
        return Ticket.builder()
                .withId(entity.getId())
                .withUserId(entity.getUserId())
                .withExhibitionId(entity.getExhibitionId())
                .withIsBought(entity.isBought())
                .build();
    }

    @Override
    public TicketEntity mapDomainToEntity(Ticket item) {
        return TicketEntity.builder()
                .withId(Optional.ofNullable(item.getId()).orElse(0L))
                .withUserId(item.getUserId())
                .withExhibitionId(Optional.ofNullable(item.getExhibitionId()).orElse(0L))
                .withIsBought(item.isBought())
                .build();
    }
}
