package com.exhibition.app.command.user;

import com.exhibition.app.domain.Ticket;
import com.exhibition.app.domain.User;
import com.exhibition.app.exception.FailException;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.impl.UserServiceImpl;
import com.exhibition.app.service.validator.UserValidator;
import com.exhibition.app.service.validator.impl.UserValidatorImpl;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SignUpCommandTest {
    public static final String VALID_USERNAME = "Liza";
    public static final String VALID_EMAIL = "liza@gmail.com";
    public static final String NOT_VALID_EMAIL = "liza.com";
    public static final String VALID_PASS = DigestUtils.md5Hex("123");
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale/messages_en", Locale.getDefault());
    public static final User USER = User.builder()
            .withId(1L)
            .withUsername(VALID_USERNAME)
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
    private Localization localization;

    @InjectMocks
    private SignUpCommand signUpCommand;

    @Test
    public void show() {
        String url = signUpCommand.show(request);
        assertNotNull(url);
    }

    @Test
    public void executeShouldBeUnsuccessful() {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password1")).thenReturn("");
        when(request.getParameter("password2")).thenReturn("");
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = signUpCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/signUp.jsp", url);
        verify(request, times(4)).getParameter(anyString());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldBeUnsuccessful2() {
        when(request.getParameter("username")).thenReturn(VALID_USERNAME);
        when(request.getParameter("email")).thenReturn(NOT_VALID_EMAIL);
        when(request.getParameter("password1")).thenReturn(VALID_PASS);
        when(request.getParameter("password2")).thenReturn("");
        when(userService.isUserExist(NOT_VALID_EMAIL)).thenReturn(Optional.of(USER));
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = signUpCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/signUp.jsp", url);
        verify(request, times(4)).getParameter(anyString());
        verify(userService).isUserExist(NOT_VALID_EMAIL);
        verify(localization).getLocalizationBundle(request);
    }


    @Test
    public void executeShouldBeSuccessful() {
        when(request.getParameter("username")).thenReturn(VALID_USERNAME);
        when(request.getParameter("email")).thenReturn(VALID_EMAIL);
        when(request.getParameter("password1")).thenReturn(VALID_PASS);
        when(request.getParameter("password2")).thenReturn(VALID_PASS);
        when(userService.isUserExist(VALID_EMAIL)).thenReturn(Optional.empty());
        doNothing().when(userService).register(any());
        when(request.getSession()).thenReturn(session);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = signUpCommand.execute(request);

        assertNotNull(url);
        assertEquals("/index", url);
        verify(request, times(4)).getParameter(anyString());
        verify(userService).isUserExist(VALID_EMAIL);
        verify(request).getSession();
        verify(userService).register(any());
        verify(localization).getLocalizationBundle(request);
    }

}