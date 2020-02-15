package com.exhibition.app.controller;

import com.exhibition.app.command.Command;
import com.exhibition.app.injector.ApplicationInjector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FrontController", urlPatterns = {"/login", "/signUp", "/logout", "/index", "/exhibition",
        "/buying", "/purchase", "/removeTicket", "/pay", "/profile"})
public class FrontController extends HttpServlet {
    private final Map<String, Command> commands;

    public FrontController() {
        this.commands = ApplicationInjector.getCommands();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void showRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        String page = command.show(request);
        processCommand(request, response, page);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        String page = command.execute(request);
        processCommand(request, response, page);
    }

    private void processCommand(HttpServletRequest request, HttpServletResponse response, String page) throws IOException, ServletException {
        if (!page.contains(".jsp")) {
            response.sendRedirect(request.getContextPath() + page);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        if(!commands.containsKey(path)) {
            return commands.get("/login");
        }
        return commands.get(path);
    }
}
