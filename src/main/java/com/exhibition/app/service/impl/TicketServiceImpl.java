package com.exhibition.app.service.impl;

import com.exhibition.app.dao.Page;
import com.exhibition.app.dao.TicketDao;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.entity.TicketEntity;
import com.exhibition.app.service.TicketService;
import com.exhibition.app.service.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {
    final TicketDao ticketDao;
    final Mapper<TicketEntity, Ticket> ticketMapper;

    public TicketServiceImpl(TicketDao ticketDao, Mapper<TicketEntity, Ticket> ticketMapper) {
        this.ticketDao = ticketDao;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public void add(long userId, long exhibitionId) {
        final Ticket temp = Ticket.builder()
                .withUserId(userId)
                .withExhibitionId(exhibitionId)
                .withIsBought(false)
                .build();
        final TicketEntity ticket = ticketMapper.mapDomainToEntity(temp);
        ticketDao.save(ticket);
    }

    @Override
    public List<Ticket> getUserExhibsByUserId(long userId, boolean isBought) {
        return ticketDao.findByUserIdAndIsBought(userId, isBought)
                .stream().map(ticketMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteTicketById(long ticketId) {
        ticketDao.delete(ticketId);
    }

    @Override
    public void updateIsBought(long userId) {
        final Ticket temp = Ticket.builder()
                .withUserId(userId)
                .withIsBought(true)
                .build();
        final TicketEntity ticket = ticketMapper.mapDomainToEntity(temp);
        ticketDao.update(ticket);
    }

    @Override
    public List<Ticket> getPageOfBoughtTickets(long userId, Page page) {
        return ticketDao.findAll(userId, page).stream()
                .map(ticketMapper::mapEntityToDomain).collect(Collectors.toList());
    }
}
