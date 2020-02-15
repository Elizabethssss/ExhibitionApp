package com.exhibition.app.command.exhibition;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class IndexCommand implements Command {
    private final ExhibitionService exhibitionService;

    public IndexCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final int inCart = (int) session.getAttribute("inCart");

        final Map<String, List<Exhibition>> exhibMap = exhibitionService.getMonthExhibitionsMap();

        request.setAttribute("exhibMap", exhibMap);
        request.setAttribute("name", session.getAttribute("user"));
        request.setAttribute("inCart", inCart);
        return "pages/index.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/index.jsp";
    }
}
