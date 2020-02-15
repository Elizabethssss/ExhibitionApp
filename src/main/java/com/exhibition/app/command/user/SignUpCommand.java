package com.exhibition.app.command.user;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.User;
import com.exhibition.app.exception.FailException;
import com.exhibition.app.injector.ApplicationInjector;
import com.exhibition.app.service.UserService;
import com.exhibition.app.service.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.exhibition.app.exception.ErrorTypes.EMAIL_INPUT_ERROR;

public class SignUpCommand implements Command {
    private final UserService userService;
    private final UserValidator userValidator;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
        this.userValidator = ApplicationInjector.getUserValidator();
    }

    @Override
    public String show(HttpServletRequest request) {
        return "pages/signUp.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String username = request.getParameter("username").trim();
        final String email = request.getParameter("email").trim();
        final String password1 = request.getParameter("password1");
        final String password2 = request.getParameter("password2");

        String alarm = "Fill all parameters!";
        User user;
        boolean newUser = true;

        if(username.equals("")) {
            request.setAttribute("errorUsername", true);
            newUser = false;
        }

        if (email.equals("")) {
            request.setAttribute("errorEmail", true);
            newUser = false;
        }
        else {
            user = User.builder()
                    .withEmail(email)
                    .build();

            if(userService.isUserExist(email).isPresent()) {
                alarm = "Email is already used!";
                request.setAttribute("errorEmail", true);
                newUser = false;
            }
            try {
                userValidator.validate(user);
            } catch (FailException e) {
                if (e.getErrorCode() == EMAIL_INPUT_ERROR) {
                    alarm = "Wrong email!";
                    request.setAttribute("errorEmail", true);
                    newUser = false;
                }
            }
        }

        if(password1.equals("") || password2.equals("")) {
            request.setAttribute("errorPassword", true);
            newUser = false;
        }
        if(!password1.equals(password2)) {
            alarm = "Passwords are not equal!";
            request.setAttribute("errorPassword", true);
            newUser = false;
        }

        if(newUser) {
            user = User.builder()
                    .withUsername(username)
                    .withEmail(email)
                    .withPassword(password1)
                    .build();

            userService.register(user);

            HttpSession session = request.getSession();
            session.setAttribute("user", Optional.of(user));
            session.setAttribute("inCart", 0);
            return "/index";
        }
        else {
            request.setAttribute("alarm", alarm);
            setSignUpAttributes(request, username, email);
            return "pages/signUp.jsp";
        }
    }

    private void setSignUpAttributes(HttpServletRequest request, String username, String email) {
        request.setAttribute("username", username);
        request.setAttribute("email", email);
    }
}
