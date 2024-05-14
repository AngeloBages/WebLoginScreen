package com.loginscreen.servlets;

import java.io.IOException;
import com.loginscreen.utils.ServletAction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/menu"})
public class DisplayMenuServlet extends HttpServlet implements ServletAction {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("paginaDestino", "/WEB-INF/jsps/index.jsp");
    }

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        req.getSession().setAttribute("action", "ValidarLoginServlet");
        return (String) req.getSession().getAttribute("paginaDestino");
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }
}