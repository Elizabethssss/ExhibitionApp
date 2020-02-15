package com.exhibition.app.command.user;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String show(HttpServletRequest request) {
        return "pages/login.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email").trim();
        final String password = DigestUtils.md5Hex(request.getParameter("password"));

        String alarm = "Wrong username or password!";

        if (!userService.login(email, password)) {
            request.setAttribute("error", true);
            request.setAttribute("email", email);
            request.setAttribute("alarm", alarm);
            return "pages/login.jsp";
        } else {
            final HttpSession session = request.getSession();
            final Optional<User> user = userService.isUserExist(email);
            session.setAttribute("user", user);
            //TODO: get user's in cart
            session.setAttribute("inCart", 0);
            return "/index";
        }
    }
}
