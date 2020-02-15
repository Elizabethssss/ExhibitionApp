package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.Ticket;
import com.exhibition.app.entity.TicketEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketMapperTest {
    private static final Long ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Long EXHIBITION_ID = 1L;
    private static final boolean IS_BOUGHT = true;

    private TicketMapper ticketMapper = new TicketMapper();

    @Mock
    private TicketEntity ticketEntity;

    @Mock
    private Ticket ticket;

    @Test
    public void mapEntityToDomainShouldReturnTicket() {
        when(ticketEntity.getId()).thenReturn(ID);
        when(ticketEntity.getUserId()).thenReturn(USER_ID);
        when(ticketEntity.getExhibitionId()).thenReturn(EXHIBITION_ID);
        when(ticketEntity.isBought()).thenReturn(IS_BOUGHT);

        Ticket ticket = ticketMapper.mapEntityToDomain(ticketEntity);

        assertEquals(ID, ticket.getId());
        assertEquals(USER_ID, ticket.getUserId());
        assertEquals(EXHIBITION_ID, ticket.getExhibitionId());
        assertEquals(IS_BOUGHT, ticket.isBought());
   }

    @Test
    public void mapDomainToEntityShouldReturnTicketEntity() {
        when(ticket.getId()).thenReturn(ID);
        when(ticket.getUserId()).thenReturn(USER_ID);
        when(ticket.getExhibitionId()).thenReturn(EXHIBITION_ID);
        when(ticket.isBought()).thenReturn(IS_BOUGHT);

        TicketEntity ticketEntity = ticketMapper.mapDomainToEntity(ticket);

        assertEquals(ID, ticketEntity.getId());
        assertEquals(USER_ID, ticketEntity.getUserId());
        assertEquals(EXHIBITION_ID, ticketEntity.getExhibitionId());
        assertEquals(IS_BOUGHT, ticketEntity.isBought());
    }
}