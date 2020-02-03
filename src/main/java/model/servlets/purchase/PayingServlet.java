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
import java.time.LocalDate;

@WebServlet(name = "PayingServlet", urlPatterns = {"/pay"})
public class PayingServlet extends HttpServlet {
    private final DBUserExhibManager userExhibManager = new DBUserExhibManager();
    private final DBUserManager userManager = new DBUserManager();
    private static final String CARD_INPUT = "[0-9]*";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionName = (String) session.getAttribute("name");

        if (sessionName == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            long totalPrice = (long) session.getAttribute("totalPrice");
            int inCart = (int) session.getAttribute("inCart");

            String message = (String) session.getAttribute("message");
            session.removeAttribute("message");
            request.setAttribute("username", sessionName);
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("inCart", inCart);
            request.setAttribute("message", message);
            request.getRequestDispatcher("pages/creditCard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardNumber = request.getParameter("cardNumber").trim();
        String cardMonth = request.getParameter("cardMonth").trim();
        String cardYear = request.getParameter("cardYear").trim();
        String cardCVV = request.getParameter("cardCVV").trim();

        HttpSession session = request.getSession();
        String sessionName = (String) session.getAttribute("name");

        String errorMessage = "Fill all parameters!";
        boolean payed = true;

        if (cardNumber.equals("")) {
            request.setAttribute("errorNumber", true);
            payed = false;
        } else if (checkCreditCardNumber(cardNumber)) {
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
        } else if (!cardYear.matches(CARD_INPUT) || !cardMonth.matches(CARD_INPUT) || Integer.parseInt(cardMonth) > 12 ||
                (LocalDate.now().getYear() - 2000 > Integer.parseInt(cardYear) &&
                LocalDate.now().getMonthValue() > Integer.parseInt(cardMonth))) {
            errorMessage = "Wrong date!";
            request.setAttribute("errorDate", true);
            payed = false;
        }
        if (cardCVV.equals("")) {
            request.setAttribute("errorPassword", true);
            payed = false;
        }
        else if(!cardCVV.matches(CARD_INPUT) || cardCVV.length() != 3) {
            errorMessage = "Wrong CVV!";
            payed = false;
        }

        if (payed) {
            long id = userManager.getIdByUsername(sessionName);
            userExhibManager.updateIsBought(id);
            session.setAttribute("totalPrice", 0L);
            session.setAttribute("inCart", 0);
            session.setAttribute("message", "Paid successfully!");
            response.sendRedirect(request.getContextPath() + "/pay");
        }
        else {
            request.setAttribute("errorMessage", errorMessage);
            setCardAttributes(request, cardNumber, cardMonth, cardYear);
            request.getRequestDispatcher("pages/creditCard.jsp").forward(request, response);
        }
    }

    private boolean checkCreditCardNumber(String cardNumber) {
        return !cardNumber.matches(CARD_INPUT) || !validateCreditCardNumber(cardNumber) || cardNumber.length() <= 10;
    }

    private void setCardAttributes(HttpServletRequest request, String cardNumber, String cardMonth, String cardYear) {
        request.setAttribute("cardNumber", cardNumber);
        request.setAttribute("cardMonth", cardMonth);
        request.setAttribute("cardYear", cardYear);
    }

    private boolean validateCreditCardNumber(String str) {
        int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i -= 2) {
            int j = ints[i];
            j *= 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int anInt : ints) {
            sum += anInt;
        }
        return sum % 10 == 0;
    }
}
