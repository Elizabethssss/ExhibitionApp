package com.exhibition.app.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String show(HttpServletRequest request);
    String execute(HttpServletRequest request);
}
