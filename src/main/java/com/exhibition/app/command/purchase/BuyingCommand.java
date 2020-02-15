package com.exhibition.app.command.purchase;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class BuyingCommand implements Command {
    final TicketService ticketService;

    public BuyingCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String show(HttpServletRequest request) {
        return "pages/exhibition.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Optional<User> user = (Optional<User>) session.getAttribute("user");

        final long exhibitionId = Long.parseLong(request.getParameter("id"));
        final int page = Integer.parseInt(request.getParameter("page"));
        final long userId = user.get().getId();

        int inCart = (int) session.getAttribute("inCart");
        final String message = "Added to cart!";

        ticketService.add(userId, exhibitionId);
        inCart++;

        session.setAttribute("inCart", inCart);
        session.setAttribute("message", message);

        return "/exhibition?id=" + exhibitionId + "&page=" + page;
    }
}
