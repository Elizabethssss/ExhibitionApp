package com.exhibition.app.command.exhibition;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.service.ExhibitionService;
import com.exhibition.app.service.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class IndexCommand implements Command {
    private final ExhibitionService exhibitionService;
    private final Localization localization;

    public IndexCommand(ExhibitionService exhibitionService, Localization localization) {
        this.exhibitionService = exhibitionService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final int inCart = (int) session.getAttribute("inCart");

        final ResourceBundle bundle = localization.getLocalizationBundle(request);
        request.setAttribute("bundle", bundle);

        final Map<Integer, List<Exhibition>> integerMap = exhibitionService.getMonthExhibitionsMap();
        final Map<String, List<Exhibition>> exhibMap = putMonths(integerMap, bundle);
        request.setAttribute("exhibMap", exhibMap);
        request.setAttribute("name", session.getAttribute("user"));
        request.setAttribute("inCart", inCart);
        return "pages/index.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/index.jsp";
    }

    private Map<String, List<Exhibition>> putMonths(Map<Integer, List<Exhibition>> integerMap, ResourceBundle bundle) {
        Map<String, List<Exhibition>> result = new LinkedHashMap<>();
        String[] month = bundle.getString("months").split(",");
        integerMap.forEach((k, v) -> result.put(month[k], v));
        return result;
    }
}
