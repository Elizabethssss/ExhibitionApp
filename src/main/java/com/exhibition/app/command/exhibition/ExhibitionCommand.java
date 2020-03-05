package com.exhibition.app.command.exhibition;

import com.exhibition.app.command.Command;
import com.exhibition.app.dao.Page;
import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.domain.Exposition;
import com.exhibition.app.service.ExhibitionService;
import com.exhibition.app.service.ExpositionService;
import com.exhibition.app.service.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ExhibitionCommand implements Command {
    final ExhibitionService exhibitionService;
    final ExpositionService expositionService;
    private final Localization localization;

    public ExhibitionCommand(ExhibitionService exhibitionService, ExpositionService expositionService, Localization localization) {
        this.exhibitionService = exhibitionService;
        this.expositionService = expositionService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        request.setAttribute("bundle", localization.getLocalizationBundle(request));

        final long id = Long.parseLong(request.getParameter("id"));
        final int inCart = (int) session.getAttribute("inCart");
        final String message = (String) session.getAttribute("message");
        final int numberOfExpositions = expositionService.count(id);
        session.removeAttribute("message");

        int pageNumber = Integer.parseInt(request.getParameter("page"));
        if(pageNumber < 1 || pageNumber > Math.ceil(numberOfExpositions/3.)) {
            pageNumber = 1;
        }
        final Page page = new Page( (pageNumber - 1) * 3, 3 );

        final Optional<Exhibition> exhib = exhibitionService.getExhibitionById(id);
        final List<Exposition> expositions = expositionService.getExpositionsByExhibId(id, page);

        setExpositionAttributes(request, session, inCart, numberOfExpositions, pageNumber, exhib, expositions, message);
        return "pages/exhibition.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/exhibition.jsp";
    }

    private void setExpositionAttributes(HttpServletRequest request, HttpSession session, int inCart, int numberOfExpositions,
                                         int pageNumber, Optional<Exhibition> exhib, List<Exposition> expositions, String message) {
        request.setAttribute("user",  session.getAttribute("user"));
        request.setAttribute("exhib", exhib);
        request.setAttribute("expositions", expositions);
        request.setAttribute("inCart", inCart);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("numberOfExpositions", numberOfExpositions);
        request.setAttribute("message", message);
    }
}
