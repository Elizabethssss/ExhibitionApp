package com.exhibition.app.service.impl;

import com.exhibition.app.dao.Page;
import com.exhibition.app.dao.TicketDao;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.entity.TicketEntity;
import com.exhibition.app.service.TicketService;
import com.exhibition.app.service.mapper.TicketMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {
    public static final Ticket TICKET = Ticket.builder()
            .withId(1L)
            .withUserId(1L)
            .withExhibitionId(1L)
            .withIsBought(false)
            .build();
    public static final TicketEntity TICKET_ENTITY = TicketEntity.builder()
            .withId(1L)
            .withUserId(1L)
            .withExhibitionId(1L)
            .withIsBought(false)
            .build();
    public static final Page PAGE = new Page(1, 0);
    private static List<Ticket> TICKET_LIST;
    private static List<TicketEntity> ENTITY_LIST;

    @Mock
    private TicketDao ticketDao;

    @Mock
    private TicketMapper ticketMapper;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Before
    public void init() {
        TICKET_LIST = new ArrayList<>();
        ENTITY_LIST = new ArrayList<>();
        TICKET_LIST.add(TICKET);
        ENTITY_LIST.add(TICKET_ENTITY);
    }

    @Test
    public void addShouldBeSuccessful() {
        when(ticketDao.save(TICKET_ENTITY)).thenReturn(true);
        when(ticketMapper.mapDomainToEntity(any())).thenReturn(TICKET_ENTITY);

        ticketService.add(TICKET.getUserId(), TICKET.getExhibitionId());

        verify(ticketDao).save(TICKET_ENTITY);
        verify(ticketMapper).mapDomainToEntity(any());
    }

    @Test
    public void getUserExhibsByUserIdShouldReturnList() {
        when(ticketDao.findByUserIdAndIsBought(TICKET_ENTITY.getUserId(), TICKET_ENTITY.isBought()))
                .thenReturn(ENTITY_LIST);
        when(ticketMapper.mapEntityToDomain(TICKET_ENTITY)).thenReturn(TICKET);

        assertTrue(ticketService.getUserExhibsByUserId(TICKET.getUserId(), TICKET.isBought()).size() > 0);

        verify(ticketDao).findByUserIdAndIsBought(TICKET_ENTITY.getUserId(), TICKET_ENTITY.isBought());
        verify(ticketMapper).mapEntityToDomain(TICKET_ENTITY);
    }

    @Test
    public void deleteTicketByIdShouldBeSuccessful() {
        when(ticketDao.delete(TICKET_ENTITY.getId())).thenReturn(true);

        ticketService.deleteTicketById(TICKET.getId());

        verify(ticketDao).delete(TICKET_ENTITY.getId());
    }

    @Test
    public void updateIsBoughtShouldBeSuccessful() {
        when(ticketDao.update(TICKET_ENTITY)).thenReturn(true);
        when(ticketMapper.mapDomainToEntity(any())).thenReturn(TICKET_ENTITY);

        ticketService.updateIsBought(TICKET.getUserId());

        verify(ticketDao).update(TICKET_ENTITY);
        verify(ticketMapper).mapDomainToEntity(any());
    }

    @Test
    public void getPageOfBoughtTicketsShouldReturnList() {
        when(ticketDao.findAll(TICKET_ENTITY.getUserId(), PAGE))
                .thenReturn(ENTITY_LIST);
        when(ticketMapper.mapEntityToDomain(TICKET_ENTITY)).thenReturn(TICKET);

        assertTrue(ticketService.getPageOfBoughtTickets(TICKET.getUserId(), PAGE).size() > 0);

        verify(ticketDao).findAll(TICKET_ENTITY.getUserId(), PAGE);
        verify(ticketMapper).mapEntityToDomain(TICKET_ENTITY);
    }
}