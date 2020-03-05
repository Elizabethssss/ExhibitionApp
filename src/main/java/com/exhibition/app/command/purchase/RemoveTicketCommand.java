package com.exhibition.app.command.purchase;

import com.exhibition.app.command.Command;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveTicketCommand implements Command {
    final TicketService ticketService;
    final Localization localization;

    public RemoveTicketCommand(TicketService ticketService, Localization localization) {
        this.localization = localization;
        this.ticketService = ticketService;
    }

    @Override
    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession();
        final long ticketId = Long.parseLong(request.getParameter("id"));
        int inCart = (int) session.getAttribute("inCart");
        request.setAttribute("bundle", localization.getLocalizationBundle(request));

        ticketService.deleteTicketById(ticketId);
        inCart--;

        session.setAttribute("inCart", inCart);
        request.setAttribute("inCart", inCart);
        return "/purchase?lang=" + session.getAttribute("locale");
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/purchase.jsp";
    }
}
