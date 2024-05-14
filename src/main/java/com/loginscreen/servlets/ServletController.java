
package com.loginscreen.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.loginscreen.utils.ServletAction;

@WebServlet(urlPatterns = {"/"})
public class ServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_ACTION = "DisplayMenuServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? DEFAULT_ACTION : req.getParameter("action");
        req.getSession().setAttribute("action", action);
        processRequest(req, resp, action, true);
        req.getSession().removeAttribute("mensagem");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getSession().getAttribute("action");
        processRequest(req, resp, action, false);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp, String action, boolean isGet)
            throws ServletException, IOException {
        ServletAction servlet = loadServlet(action);
        if (servlet == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found: " + action);
            return;
        }

        String paginaJsp = isGet ? servlet.executeGet(req, resp) : servlet.executePost(req, resp);
        if (paginaJsp != null) {
            if (isGet) {
                req.getRequestDispatcher(paginaJsp).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + paginaJsp);
            }
        }
    }

    private ServletAction loadServlet(String action) {
        String servletClass = "com.loginscreen.servlets." + action;
        try {
            Class<?> clazz = Class.forName(servletClass);
            return (ServletAction) clazz.getConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}