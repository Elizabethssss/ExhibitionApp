package com.exhibition.app.filter;

import com.exhibition.app.injector.ApplicationInjector;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession();
        String locale = req.getParameter("lang");
        if (!Arrays.asList(ApplicationInjector.LANGUAGES).contains(locale)) {
            locale = ApplicationInjector.LANGUAGE_DEFAULT;
//            session.setAttribute("locale", locale);
//            String queryString = ((HttpServletRequest) request).getQueryString();
//            String newURL = queryString.substring(0, queryString.lastIndexOf("=") + 1) + "en";
//            request.getRequestDispatcher(((HttpServletRequest) request).getRequestURI() + "?" + newURL).forward(request, response);
        }
            session.setAttribute("locale", locale);
            chain.doFilter(req, res);

    }
}
