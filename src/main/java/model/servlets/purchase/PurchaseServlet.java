package model.servlets.purchase;

import controller.DBExhibitionManager;
import controller.DBUserExhibManager;
import controller.DBUserManager;
import model.db.entity.Exhibition;
import model.db.entity.UserExhib;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "PurchaseServlet", urlPatterns = {"/purchase"})
public class PurchaseServlet extends HttpServlet {
    private final DBUserExhibManager userExhibManager = new DBUserExhibManager();
    private final DBUserManager userManager = new DBUserManager();
    private final DBExhibitionManager exhibitionManager = new DBExhibitionManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionName = (String) session.getAttribute("name");

        if (sessionName == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            long userId = userManager.getIdByUsername(sessionName);
            long totalPrice = 0;
            List<UserExhib> userExhibs = userExhibManager.getUserExhibsByUserId(userId, false);
            Map<Long, Optional<Exhibition>> ticketsMap = new LinkedHashMap<>();
            for (UserExhib userExhib : userExhibs) {
                Optional<Exhibition> temp = exhibitionManager.getExhibitionById(userExhib.getExhibitionId());
                ticketsMap.put(userExhib.getId(), temp);
                totalPrice += temp.get().getPrice();
            }

            session.setAttribute("totalPrice", totalPrice);
            request.setAttribute("username", sessionName);
            request.setAttribute("ticketsMap", ticketsMap);
            request.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher("pages/purchase.jsp").forward(request, response);
        }
    }
}