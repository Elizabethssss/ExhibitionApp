package com.exhibition.app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/index", "/exhibition", "/purchase", "/pay", "/profile", "/"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        boolean isUserInSession = session != null && session.getAttribute("user") != null;
        if(!isUserInSession) {
            response.sendRedirect(request.getContextPath() + "/login?lang=en");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
