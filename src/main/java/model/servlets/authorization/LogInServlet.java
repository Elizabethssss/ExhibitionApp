package model.servlets.authorization;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if(name == null) {
            request.getRequestDispatcher("pages/LogIn.jsp").forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBUserManager dbUserManager = new DBUserManager();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password");

        String alarm = "Fill all parameters!";

        if(username.equals("")) {
            request.setAttribute("errorUsername", true);
            request.setAttribute("alarm", alarm);
        }
        if(password.equals("")) {
            request.setAttribute("username", username);
            request.setAttribute("errorPassword", true);
            request.setAttribute("alarm", alarm);
        }
        password = DigestUtils.md5Hex(password);
        if(!dbUserManager.findUser(username, password)) {
            alarm = "Wrong username or password!";
            request.setAttribute("username", username);
            request.setAttribute("errorUsername", true);
            request.setAttribute("errorPassword", true);
            request.setAttribute("alarm", alarm);
            request.getRequestDispatcher("pages/LogIn.jsp").forward(request, response);
        }
        else {
            request.setAttribute("username", username);
            HttpSession session = request.getSession();
            session.setAttribute("name", username);
            response.sendRedirect(request.getContextPath() + "/index");
        }

    }
}
