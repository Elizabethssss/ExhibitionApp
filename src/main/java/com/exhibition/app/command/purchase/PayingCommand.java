package com.exhibition.app.command.purchase;

import com.exhibition.app.command.Command;
import com.exhibition.app.domain.User;
import com.exhibition.app.injector.ApplicationInjector;
import com.exhibition.app.service.TicketService;
import com.exhibition.app.service.validator.CreditCardValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class PayingCommand implements Command {
    final TicketService ticketService;
    final CreditCardValidator creditCardValidator;

    public PayingCommand(TicketService ticketService) {
        this.ticketService = ticketService;
        this.creditCardValidator = ApplicationInjector.getCreditCardValidator();
    }

    @Override
    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession();

        final long totalPrice = (long) session.getAttribute("totalPrice");
        final int inCart = (int) session.getAttribute("inCart");
        final String message = (String) session.getAttribute("message");
        session.removeAttribute("message");

        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("inCart", inCart);
        request.setAttribute("message", message);
        return "pages/creditCard.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String cardNumber = request.getParameter("cardNumber").trim();
        final String cardMonth = request.getParameter("cardMonth").trim();
        final String cardYear = request.getParameter("cardYear").trim();
        final String cardCVV = request.getParameter("cardCVV").trim();

        final HttpSession session = request.getSession();
        final Optional<User> user = (Optional<User>) session.getAttribute("user");
        final long userId = user.get().getId();

        String errorMessage = "Fill all parameters!";
        boolean payed = true;

        if (cardNumber.equals("")) {
            request.setAttribute("errorNumber", true);
            payed = false;
        }
        else if (!creditCardValidator.validateNumberInput(cardNumber) ||
                !creditCardValidator.validateCreditCardNumber(cardNumber)) {
            errorMessage = "Not valid card number!";
            request.setAttribute("errorNumber", true);
            payed = false;
        }
        if (cardMonth.equals("")) {
            request.setAttribute("errorDate", true);
            payed = false;
        }
        if (cardYear.equals("")) {
            request.setAttribute("errorDate", true);
            payed = false;
        }
        else if (!creditCardValidator.validateDateInput(cardMonth, cardYear)) {
            errorMessage = "Wrong date!";
            request.setAttribute("errorDate", true);
            payed = false;
        }
        if (cardCVV.equals("")) {
            request.setAttribute("errorPassword", true);
            payed = false;
        }
        else if(!creditCardValidator.validateCVVInput(cardCVV)) {
            errorMessage = "Wrong CVV!";
            payed = false;
        }

        if (payed) {
            ticketService.updateIsBought(userId);
            session.setAttribute("totalPrice", 0L);
            session.setAttribute("inCart", 0);
            session.setAttribute("message", "Paid successfully!");
            return "/pay";
        }
        else {
            request.setAttribute("errorMessage", errorMessage);
            setCardAttributes(request, cardNumber, cardMonth, cardYear);
            return "pages/creditCard.jsp";
        }
    }

    private void setCardAttributes(HttpServletRequest request, String cardNumber, String cardMonth, String cardYear) {
        request.setAttribute("cardNumber", cardNumber);
        request.setAttribute("cardMonth", cardMonth);
        request.setAttribute("cardYear", cardYear);
    }
}
