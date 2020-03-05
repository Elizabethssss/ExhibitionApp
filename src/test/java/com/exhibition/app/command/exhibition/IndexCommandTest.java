package com.exhibition.app.command.exhibition;

import com.exhibition.app.command.user.SignUpCommand;
import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.impl.ExhibitionServiceImpl;
import com.exhibition.app.service.impl.UserServiceImpl;
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
public class IndexCommandTest {
    public static final Map<Integer, List<Exhibition>> MAP = new LinkedHashMap();
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale/messages_en", Locale.getDefault());

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private ExhibitionServiceImpl exhibitionService;
    @Mock
    private Localization localization;

    @InjectMocks
    private IndexCommand indexCommand;

    @Test
    public void show() {
        when(request.getSession()).thenReturn(session);
        when(exhibitionService.getMonthExhibitionsMap()).thenReturn(MAP);
        when(exhibitionService.getMonthExhibitionsMap()).thenReturn(MAP);
        when(session.getAttribute("inCart")).thenReturn(1);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = indexCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/index.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute("inCart");
        verify(exhibitionService).getMonthExhibitionsMap();
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void execute() {
        String url = indexCommand.execute(request);
        assertNotNull(url);
    }
}