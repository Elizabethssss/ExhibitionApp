package com.exhibition.app.command.purchase;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.ExhibitionService;
import com.exhibition.app.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PurchaseCommand implements Command {
    final TicketService ticketService;
    final ExhibitionService exhibitionService;

    public PurchaseCommand(TicketService ticketService, ExhibitionService exhibitionService) {
        this.ticketService = ticketService;
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Optional<User> user = (Optional<User>) session.getAttribute("user");
        final long userId = user.get().getId();

        final List<Ticket> userTickets = ticketService.getUserExhibsByUserId(userId, false);
        Map<Long, Optional<Exhibition>> ticketsMap = new LinkedHashMap<>();
        long totalPrice = 0;
        for (Ticket ticket : userTickets) {
            Optional<Exhibition> temp = exhibitionService.getExhibitionById(ticket.getExhibitionId());
            ticketsMap.put(ticket.getId(), temp);
            totalPrice += temp.get().getPrice();
        }

        setAttributes(request, session, user, ticketsMap, totalPrice);
        return "pages/purchase.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/purchase.jsp";
    }

    private void setAttributes(HttpServletRequest request, HttpSession session, Optional<User> user, Map<Long,
            Optional<Exhibition>> ticketsMap, long totalPrice) {
        request.setAttribute("user", user);
        request.setAttribute("ticketsMap", ticketsMap);
        session.setAttribute("totalPrice", totalPrice);
        request.setAttribute("totalPrice", totalPrice);
    }
}
