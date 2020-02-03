package model.servlets.authorization;

import controller.DBUserExhibManager;
import controller.DBUserManager;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogInServlet", urlPatterns = {"/login"})
public class LogInServlet extends HttpServlet {
    private static final String INPUT_PARAM = "(\\w|\\$|@)+";

    private final DBUserManager dbUserManager = new DBUserManager();
    private final DBUserExhibManager userExhibManager = new DBUserExhibManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if(name == null) {
            request.getRequestDispatcher("pages/authorization/LogIn.jsp").forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password");

        String alarm = "Wrong username or password!";

        if(username.equals("")) {
            request.setAttribute("errorUsername", true);
        }
        else if(!username.matches(INPUT_PARAM)) {
            alarm = "Use only a-z, A-Z, 0-9, @_$";
            request.setAttribute("errorUsername", true);
        }
        if(password.equals("")) {
            request.setAttribute("errorPassword", true);
        }
        password = DigestUtils.md5Hex(password);
        if(!dbUserManager.findUser(username, password)) {
            request.setAttribute("username", username);
            request.setAttribute("errorUsername", true);
            request.setAttribute("errorPassword", true);
            request.setAttribute("alarm", alarm);
            request.getRequestDispatcher("pages/authorization/LogIn.jsp").forward(request, response);
        }
        else {
            request.setAttribute("username", username);
            HttpSession session = request.getSession();
            session.setAttribute("name", username);
            int inCart = userExhibManager.getUserExhibsByUserId(dbUserManager.getIdByUsername(username),
                    false).size();
            session.setAttribute("inCart", inCart);
            response.sendRedirect(request.getContextPath() + "/index");
        }

    }
}
