package com.exhibition.app.command.purchase;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class BuyingCommand implements Command {
    final TicketService ticketService;
    final Localization localization;

    public BuyingCommand(TicketService ticketService, Localization localization) {
        this.ticketService = ticketService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/exhibition.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Optional<User> user = (Optional<User>) session.getAttribute("user");
        final ResourceBundle bundle = localization.getLocalizationBundle(request);

        final long exhibitionId = Long.parseLong(request.getParameter("id"));
        final int page = Integer.parseInt(request.getParameter("page"));
        final long userId = user.get().getId();

        int inCart = (int) session.getAttribute("inCart");
        final String message = bundle.getString("added");

        ticketService.add(userId, exhibitionId);
        inCart++;

        session.setAttribute("inCart", inCart);
        session.setAttribute("message", message);

        return "/exhibition?id=" + exhibitionId + "&page=" + page + "&lang=" + session.getAttribute("locale");
    }
}
