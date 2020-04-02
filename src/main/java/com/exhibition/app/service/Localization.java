package com.exhibition.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    private Locale locale = Locale.getDefault();
    private ResourceBundle bundle;

    public ResourceBundle getLocalizationBundle(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String lang = (String) session.getAttribute("locale");

        if (lang.equals("ru")) {
            locale = new Locale("ru", "RU");
            bundle = ResourceBundle.getBundle("locale/messages_ru", new UTF8Control());
        } else {
            bundle = ResourceBundle.getBundle("locale/messages_en", new UTF8Control());
        }
        return bundle;
    }
}
