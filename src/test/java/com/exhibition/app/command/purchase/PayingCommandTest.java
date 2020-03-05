package com.exhibition.app.command.purchase;

import com.exhibition.app.command.exhibition.IndexCommand;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.TicketService;
import com.exhibition.app.service.impl.ExhibitionServiceImpl;
import com.exhibition.app.service.impl.TicketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PayingCommandTest {
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale/messages_en", Locale.getDefault());
    public static final String RIGHT_CARD_NUMBER = "378282246310005";
    public static final String RIGHT_CVV = "378";
    public static final String RIGHT_MONTH = "03";
    public static final String RIGHT_YEAR = "24";

    public static final User USER = User.builder()
            .withId(1L)
            .withEmail("email@gmail.com")
            .withPassword("123")
            .build();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private TicketServiceImpl ticketService;
    @Mock
    private Localization localization;

    @InjectMocks
    private PayingCommand payingCommand;

    @Test
    public void show() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("totalPrice")).thenReturn(100500L);
        when(session.getAttribute("inCart")).thenReturn(1);
        when(session.getAttribute("message")).thenReturn("message");
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = payingCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/creditCard.jsp", url);
        verify(request).getSession();
        verify(session, times(3)).getAttribute(anyString());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldBeSuccessful() {
        when(request.getParameter("cardNumber")).thenReturn(RIGHT_CARD_NUMBER);
        when(request.getParameter("cardMonth")).thenReturn(RIGHT_MONTH);
        when(request.getParameter("cardYear")).thenReturn(RIGHT_YEAR);
        when(request.getParameter("cardCVV")).thenReturn(RIGHT_CVV);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(Optional.of(USER));
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = payingCommand.execute(request);

        assertNotNull(url);
        assertEquals("/pay", url);
        verify(request).getSession();
        verify(session).getAttribute("user");
        verify(request, times(4)).getParameter(anyString());

        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldBeUnsuccessful() {
        when(request.getParameter("cardNumber")).thenReturn("");
        when(request.getParameter("cardMonth")).thenReturn("");
        when(request.getParameter("cardYear")).thenReturn("");
        when(request.getParameter("cardCVV")).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(Optional.of(USER));
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = payingCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/creditCard.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute("user");
        verify(request, times(4)).getParameter(anyString());

        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldBeUnsuccessful2() {
        when(request.getParameter("cardNumber")).thenReturn("12");
        when(request.getParameter("cardMonth")).thenReturn("01");
        when(request.getParameter("cardYear")).thenReturn("20");
        when(request.getParameter("cardCVV")).thenReturn("12");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(Optional.of(USER));
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = payingCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/creditCard.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute("user");
        verify(request, times(4)).getParameter(anyString());

        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldBeUnsuccessful3() {
        when(request.getParameter("cardNumber")).thenReturn("1234567890123");
        when(request.getParameter("cardMonth")).thenReturn("01");
        when(request.getParameter("cardYear")).thenReturn("20");
        when(request.getParameter("cardCVV")).thenReturn("12");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(Optional.of(USER));
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = payingCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/creditCard.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute("user");
        verify(request, times(4)).getParameter(anyString());

        verify(localization).getLocalizationBundle(request);
    }
}