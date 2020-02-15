package com.exhibition.app.command.exhibition;

import com.exhibition.app.command.Command;
import com.exhibition.app.dao.Page;
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

public class ProfileCommand implements Command {
    final TicketService ticketService;
    final ExhibitionService exhibitionService;

    public ProfileCommand(TicketService ticketService, ExhibitionService exhibitionService) {
        this.ticketService = ticketService;
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Optional<User> user = (Optional<User>) session.getAttribute("user");
        final long userId = user.get().getId();

        final int numberOfTickets = ticketService.getUserExhibsByUserId(userId, true).size();
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        if(pageNumber < 1 || pageNumber > Math.ceil(numberOfTickets/5.)) {
            pageNumber = 1;
        }
        final Page page = new Page( (pageNumber - 1) * 5, 5 );

        List<Ticket> tickets = ticketService.getPageOfBoughtTickets(userId, page);
        Map<Long, Optional<Exhibition>> ticketsMap = new LinkedHashMap<>();
        for (Ticket ticket : tickets) {
            Optional<Exhibition> temp = exhibitionService.getExhibitionById(ticket.getExhibitionId());
            ticketsMap.put(ticket.getId(), temp);
        }

        setAttributes(request, numberOfTickets, pageNumber, ticketsMap);
        return "pages/myProfile.jsp";
    }

    private void setAttributes(HttpServletRequest request, int numberOfTickets, int pageNumber, Map<Long, Optional<Exhibition>> ticketsMap) {
        request.setAttribute("ticketsMap", ticketsMap);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("numberOfTickets", numberOfTickets);
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/myProfile.jsp";
    }
}
