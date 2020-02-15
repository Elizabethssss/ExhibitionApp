package com.exhibition.app.command.purchase;

import com.exhibition.app.command.Command;
import com.exhibition.app.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveTicketCommand implements Command {
    final TicketService ticketService;

    public RemoveTicketCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession();
        final long ticketId = Long.parseLong(request.getParameter("id"));
        int inCart = (int) session.getAttribute("inCart");

        ticketService.deleteTicketById(ticketId);
        inCart--;

        session.setAttribute("inCart", inCart);
        request.setAttribute("inCart", inCart);
        return "/purchase";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/purchase.jsp";
    }
}
