package com.exhibition.app.command.user;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class LogoutCommand implements Command {
    final TicketService ticketService;

    public LogoutCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Optional<User> user = (Optional<User>) session.getAttribute("user");
        final long userId = user.get().getId();

        removeUnboughtTickets(userId);

        session.invalidate();
        return "pages/login.jsp";
    }

    private void removeUnboughtTickets(long userId) {
        final List<Ticket> userTickets = ticketService.getUserExhibsByUserId(userId, false);
        for(Ticket ticket : userTickets) {
            ticketService.deleteTicketById(ticket.getId());
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/login.jsp";
    }
}
