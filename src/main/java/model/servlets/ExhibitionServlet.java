package model.servlets;

import controller.DBExhibitionManager;
import controller.DBExpositionManager;
import model.db.entity.Exhibition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ExhibitionServlet", urlPatterns = {"/exhibition"})
public class ExhibitionServlet extends HttpServlet {
    private final DBExhibitionManager exhibitionManager = new DBExhibitionManager();
    private final DBExpositionManager expositionManager = new DBExpositionManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionName = (String) session.getAttribute("name");

        if (sessionName == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            int inCart = (int) session.getAttribute("inCart");
            long id = Long.parseLong(request.getParameter("id"));
            int page = Integer.parseInt(request.getParameter("page"));
            int numberOfExpo = expositionManager.countById(id);
            String message = (String) session.getAttribute("message");
            session.removeAttribute("message");

            Optional<Exhibition> exhib = exhibitionManager.getExhibitionById(id);
            List<Exhibition.Exposition> expositions = expositionManager.getExpositionsByExhibId(id, page);

            request.setAttribute("exhib", exhib);
            request.setAttribute("expositions", expositions);
            request.setAttribute("username", sessionName);
            request.setAttribute("inCart", inCart);
            request.setAttribute("message", message);
            request.setAttribute("page", page);
            request.setAttribute("numberOfExpo", numberOfExpo);
            request.getRequestDispatcher("pages/exhibition.jsp").forward(request, response);
        }
    }
}
