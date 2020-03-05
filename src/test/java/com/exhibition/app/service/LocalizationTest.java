package com.exhibition.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocalizationTest {

    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private Localization localization;


    @Test
    public void getLocalizationBundleEN() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("locale")).thenReturn("en");

        localization.getLocalizationBundle(request);

        verify(request).getSession();
        verify(session).getAttribute("locale");
    }

    @Test
    public void getLocalizationBundleRU() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("locale")).thenReturn("ru");

        localization.getLocalizationBundle(request);

        verify(request).getSession();
        verify(session).getAttribute("locale");
    }
}