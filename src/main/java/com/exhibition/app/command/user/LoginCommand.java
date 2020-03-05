package com.exhibition.app.command.user;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.TicketService;
import com.exhibition.app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService;
    private final TicketService ticketService;
    private final Localization localization;

    public LoginCommand(UserService userService, TicketService ticketService, Localization localization) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/login.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email").trim();
        final String password = DigestUtils.md5Hex(request.getParameter("password"));

        request.setAttribute("bundle", localization.getLocalizationBundle(request));

        if (!userService.login(email, password)) {
            request.setAttribute("error", true);
            request.setAttribute("email", email);
            request.setAttribute("alarm", true);
            return "pages/login.jsp";
        } else {
            final HttpSession session = request.getSession();
            final Optional<User> user = userService.isUserExist(email);
            session.setAttribute("user", user);
            final int inCart = ticketService.getUserExhibsByUserId(user.get().getId(), false).size();
            session.setAttribute("inCart", inCart);
            return "/index?lang=" + session.getAttribute("locale");
        }
    }
}
