package com.exhibition.app.service;

import com.exhibition.app.dao.Page;
import com.exhibition.app.domain.Ticket;

import java.util.List;

public interface TicketService {
    void add(long userId, long exhibitionId);
    List<Ticket> getUserExhibsByUserId(long userId, boolean isBought);
    void deleteTicketById(long ticketId);
    void updateIsBought(long userId);
    List<Ticket> getPageOfBoughtTickets(long userId, Page page);
}
