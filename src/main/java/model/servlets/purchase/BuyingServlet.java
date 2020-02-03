package model.servlets.purchase;

import controller.DBUserExhibManager;
import controller.DBUserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BuyingServlet", urlPatterns = {"/buying"})
public class BuyingServlet extends HttpServlet {
    private final DBUserManager userManager = new DBUserManager();
    private final DBUserExhibManager userExhibManager = new DBUserExhibManager();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionName = (String) session.getAttribute("name");

        long exhibId = Long.parseLong(request.getParameter("id"));
        int page = Integer.parseInt(request.getParameter("page"));
        long userId = userManager.getIdByUsername(sessionName);
        int inCart = (int) session.getAttribute("inCart");
        String message = "Added to cart!";

        userExhibManager.add(userId, exhibId);
        inCart++;

        session.setAttribute("inCart", inCart);
        session.setAttribute("message", message);
        response.sendRedirect(request.getContextPath() + "/exhibition?id=" + exhibId + "&page=" + page);
    }
}
