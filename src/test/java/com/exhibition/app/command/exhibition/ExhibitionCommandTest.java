package com.exhibition.app.command.exhibition;

import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.domain.Exposition;
import com.exhibition.app.service.Localization;
import com.exhibition.app.service.impl.ExhibitionServiceImpl;
import com.exhibition.app.service.impl.ExpositionServiceImpl;
import com.exhibition.app.service.impl.TicketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExhibitionCommandTest {
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale/messages_en", Locale.getDefault());
    public static final Exhibition EXHIBITION = initExhibition();
    private static List<Exposition> EXPOSITION_LIST;
    public static final Exposition EXPOSITION = Exposition.builder()
            .withId(1L)
            .withName("Concert")
            .withAbout("Music")
            .withImage("/img.jpg")
            .withExhibitionId(1L)
            .build();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private ExhibitionServiceImpl exhibitionService;
    @Mock
    private ExpositionServiceImpl expositionService;
    @Mock
    private Localization localization;

    @InjectMocks
    private ExhibitionCommand exhibitionCommand;

    @Before
    public void init() {
        EXPOSITION_LIST = new ArrayList<>();
        EXPOSITION_LIST.add(EXPOSITION);
    }

    @Test
    public void show() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("page")).thenReturn("0");
        when(request.getParameter("id")).thenReturn(EXHIBITION.getId().toString());
        when(session.getAttribute("inCart")).thenReturn(3);
        when(expositionService.count(EXHIBITION.getId())).thenReturn(0);
        when(exhibitionService.getExhibitionById(EXHIBITION.getId())).thenReturn(Optional.of(EXHIBITION));
        when(expositionService.getExpositionsByExhibId(eq(EXHIBITION.getId()), any())).thenReturn(EXPOSITION_LIST);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = exhibitionCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/exhibition.jsp", url);
        verify(request).getSession();
        verify(request).getParameter("page");
        verify(request).getParameter("id");
        verify(session).getAttribute("inCart");
        verify(expositionService).count(EXHIBITION.getId());
        verify(exhibitionService).getExhibitionById(EXHIBITION.getId());
        verify(expositionService).getExpositionsByExhibId(eq(EXHIBITION.getId()), any());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void show2() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("page")).thenReturn("1");
        when(request.getParameter("id")).thenReturn(EXHIBITION.getId().toString());
        when(session.getAttribute("inCart")).thenReturn(3);
        when(expositionService.count(EXHIBITION.getId())).thenReturn(1);
        when(exhibitionService.getExhibitionById(EXHIBITION.getId())).thenReturn(Optional.of(EXHIBITION));
        when(expositionService.getExpositionsByExhibId(eq(EXHIBITION.getId()), any())).thenReturn(EXPOSITION_LIST);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = exhibitionCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/exhibition.jsp", url);
        verify(request).getSession();
        verify(request).getParameter("page");
        verify(request).getParameter("id");
        verify(session).getAttribute("inCart");
        verify(expositionService).count(EXHIBITION.getId());
        verify(exhibitionService).getExhibitionById(EXHIBITION.getId());
        verify(expositionService).getExpositionsByExhibId(eq(EXHIBITION.getId()), any());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void execute() {
        String url = exhibitionCommand.execute(request);
        assertNotNull(url);
    }

    private static Exhibition initExhibition() {
        return Exhibition.builder()
                .withId(1L)
                .withName("Exhibition")
                .withDateFrom(Date.valueOf("2020-03-03"))
                .withDateTo(Date.valueOf("2020-04-04"))
                .withTheme("Theme")
                .withAbout("About")
                .withLongAbout("Long about")
                .withPrice(1234)
                .withImage("/img.png")
                .build();
    }
}