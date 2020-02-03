package model.servlets;

import controller.DBExhibitionManager;
import model.db.entity.Exhibition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {
    private final DBExhibitionManager exhibitionManager = new DBExhibitionManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionName = (String) session.getAttribute("name");

        if(sessionName == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else {
            int inCart = (int) session.getAttribute("inCart");
            Map<String, List<Exhibition>> exhibMap = exhibitionManager.getExhibitionsByMonth();
            request.setAttribute("exhibMap", exhibMap);
            request.setAttribute("username", sessionName);
            request.setAttribute("inCart", inCart);
            request.getRequestDispatcher("pages/index.jsp").forward(request, response);
        }
    }

}
