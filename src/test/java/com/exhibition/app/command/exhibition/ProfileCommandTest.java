package com.exhibition.app.command.exhibition;

import com.exhibition.app.dao.Page;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.impl.ExhibitionServiceImpl;
import com.exhibition.app.service.impl.TicketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfileCommandTest {
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale/messages_en", Locale.getDefault());
    public static final List<Ticket> TICKETS = new ArrayList<>();
    public static List<Ticket> TICKETS_PAGE;
    public static final User USER = User.builder()
        .withId(1L)
        .withEmail("email@gmail.com")
        .withPassword("123")
        .build();
    public static final Ticket TICKET = Ticket.builder()
            .withId(1L)
            .withUserId(1L)
            .withExhibitionId(1L)
            .withIsBought(false)
            .build();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private ExhibitionServiceImpl exhibitionService;
    @Mock
    private TicketServiceImpl ticketService;
    @Mock
    private Localization localization;

    @InjectMocks
    private ProfileCommand profileCommand;

    @Before
    public void init() {
        TICKETS_PAGE = new ArrayList<>();
        TICKETS_PAGE.add(TICKET);
    }

    @Test
    public void show() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(Optional.of(USER));
        when(ticketService.getUserExhibsByUserId(USER.getId(), true)).thenReturn(TICKETS);
        when(request.getParameter("page")).thenReturn("0");
        when(ticketService.getPageOfBoughtTickets(eq(USER.getId()), any())).thenReturn(TICKETS);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = profileCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/myProfile.jsp", url);
        verify(request).getSession();
        verify(request).getParameter("page");
        verify(session).getAttribute("user");
        verify(ticketService).getUserExhibsByUserId(USER.getId(), true);
        verify(ticketService).getPageOfBoughtTickets(eq(USER.getId()), any());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void show2() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(Optional.of(USER));
        when(ticketService.getUserExhibsByUserId(USER.getId(), true)).thenReturn(TICKETS_PAGE);
        when(request.getParameter("page")).thenReturn("1");
        when(ticketService.getPageOfBoughtTickets(eq(USER.getId()), any())).thenReturn(TICKETS_PAGE);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = profileCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/myProfile.jsp", url);
        verify(request).getSession();
        verify(request).getParameter("page");
        verify(session).getAttribute("user");
        verify(ticketService).getUserExhibsByUserId(USER.getId(), true);
        verify(ticketService).getPageOfBoughtTickets(eq(USER.getId()), any());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void execute() {
        String url = profileCommand.execute(request);
        assertNotNull(url);
    }
}