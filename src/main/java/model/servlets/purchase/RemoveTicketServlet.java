package model.servlets.purchase;

import controller.DBUserExhibManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RemoveTicketServlet", urlPatterns = {"/removeTicket"})
public class RemoveTicketServlet extends HttpServlet {
    private final DBUserExhibManager userExhibManager = new DBUserExhibManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int inCart = (int) session.getAttribute("inCart");
        long ticketId = Long.parseLong(request.getParameter("id"));
        userExhibManager.deleteById(ticketId);
        inCart--;

        session.setAttribute("inCart", inCart);
        request.setAttribute("inCart", inCart);
        response.sendRedirect(request.getContextPath() + "/purchase");
    }
}
