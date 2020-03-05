package com.exhibition.app.command.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LogoutCommand logoutCommand;

    @Test
    public void showShouldBeSuccessful() {
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).invalidate();

        String url = logoutCommand.show(request);

        assertNotNull(url);
        verify(request).getSession();
        verify(session).invalidate();
    }

    @Test
    public void execute() {
        String url = logoutCommand.execute(request);
        assertNotNull(url);
    }
}