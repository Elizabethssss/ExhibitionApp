package com.exhibition.app.dao;

import com.exhibition.app.entity.TicketEntity;

import java.util.List;

public interface TicketDao extends PageableDao<TicketEntity> {
    List<TicketEntity> findByUserIdAndIsBought(Long userId, boolean isBought);
}
