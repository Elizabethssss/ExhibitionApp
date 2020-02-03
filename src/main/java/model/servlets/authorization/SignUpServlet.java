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

@WebServlet(name = "SignUpServlet", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {
    private final DBUserManager dbUserManager = new DBUserManager();
    private static final String INPUT_PARAM = "(\\w|\\$|@)+";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if(name == null) {
            request.getRequestDispatcher("pages/authorization/SignUp.jsp").forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");
        String password2 =request.getParameter("password2");

        String alarm = "Fill all parameters!";
        boolean newUser = true;

        if(email.equals("")) {
            request.setAttribute("errorEmail", true);
            newUser = false;
        }
        else if(dbUserManager.checkEmail(email)) {
            alarm = "Email is already used!";
            request.setAttribute("errorEmail", true);
            request.setAttribute("errorSameEmail", true);
            newUser = false;
        }
        if(!email.matches("(\\w+\\.?)+@(\\w+\\.?)+") && !email.matches(INPUT_PARAM)) {
            alarm = "Wrong email! Use only a-z, A-Z, 0-9, @_$";
            request.setAttribute("errorEmail", true);
            newUser = false;
        }
        if(username.equals("")) {
            request.setAttribute("errorUsername", true);
            newUser = false;
        }
        else if(dbUserManager.checkUsername(username)) {
            alarm = "Username is already used!";
            request.setAttribute("errorUsername", true);
            request.setAttribute("errorSameUsername", true);
            newUser = false;
        }
        else if(!username.matches(INPUT_PARAM)) {
            alarm = "Use only a-z, A-Z, 0-9, @_$";
            request.setAttribute("errorUsername", true);
            newUser = false;
        }
        if(password.equals("") || password2.equals("")) {
            request.setAttribute("errorPassword", true);
            newUser = false;
        }
        if(!password.equals(password2)) {
            alarm = "Passwords are not equal!";
            request.setAttribute("errorPassword", true);
            newUser = false;
        }
        password =  DigestUtils.md5Hex(password);

        if(newUser) {
            if(!dbUserManager.addUserToDB(username, email, password)) {
                alarm = "Problems!";
                request.setAttribute("errorUsername", true);
                request.setAttribute("errorSameUsername", true);
                request.setAttribute("alarm", alarm);
            }
           else {
                HttpSession session = request.getSession();
                session.setAttribute("name", username);
                session.setAttribute("inCart", 0);
                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
        else {
            request.setAttribute("alarm", alarm);
            setSignUpAttributes(request, username, email);

            request.getRequestDispatcher("pages/authorization/SignUp.jsp").forward(request, response);
        }

    }

    private void setSignUpAttributes(HttpServletRequest request, String username, String email) {
        request.setAttribute("username", username);
        request.setAttribute("email", email);
    }
}
