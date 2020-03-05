package com.exhibition.app.command.user;

import com.exhibition.app.domain.Ticket;
import com.exhibition.app.domain.User;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.TicketService;
import com.exhibition.app.service.UserService;
import com.exhibition.app.service.impl.TicketServiceImpl;
import com.exhibition.app.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {
    public static final String VALID_EMAIL = "liza@gmail.com";
    public static final String VALID_PASS = DigestUtils.md5Hex("123");
    public static final String NOT_VALID_PASS = "abc";
    public static final List<Ticket> LIST = new ArrayList();
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale/messages_en", Locale.getDefault());
    public static final User USER = User.builder()
            .withId(1L)
            .withEmail(VALID_EMAIL)
            .withPassword(VALID_PASS)
            .build();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private TicketServiceImpl ticketService;
    @Mock
    private Localization localization;

    @InjectMocks
    private LoginCommand loginCommand;

    @Test
    public void show() {
        String url = loginCommand.show(request);
        assertNotNull(url);
    }

    @Test
    public void executeShouldReturnPageUrl() {
        when(request.getParameter("email")).thenReturn(VALID_EMAIL);
        when(request.getParameter("password")).thenReturn(NOT_VALID_PASS);
        when(userService.login(VALID_EMAIL, DigestUtils.md5Hex(NOT_VALID_PASS))).thenReturn(false);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = loginCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/login.jsp", url);
        verify(userService).login(VALID_EMAIL, DigestUtils.md5Hex(NOT_VALID_PASS));
        verify(request, times(2)).getParameter(anyString());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldReturnCommandUrl() {
        when(request.getParameter("email")).thenReturn(VALID_EMAIL);
        when(request.getParameter("password")).thenReturn(VALID_PASS);
        when(userService.login(VALID_EMAIL, DigestUtils.md5Hex(VALID_PASS))).thenReturn(true);
        when(request.getSession()).thenReturn(session);
        when(userService.isUserExist(VALID_EMAIL)).thenReturn(Optional.of(USER));
        when(ticketService.getUserExhibsByUserId(USER.getId(), false)).thenReturn(LIST);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = loginCommand.execute(request);

        assertNotNull(url);
        assertTrue(url.contains("/index"));
        verify(request, times(2)).getParameter(anyString());
        verify(userService).login(VALID_EMAIL, DigestUtils.md5Hex(VALID_PASS));
        verify(userService).isUserExist(VALID_EMAIL);
        verify(ticketService).getUserExhibsByUserId(USER.getId(), false);
        verify(localization).getLocalizationBundle(request);
    }

}