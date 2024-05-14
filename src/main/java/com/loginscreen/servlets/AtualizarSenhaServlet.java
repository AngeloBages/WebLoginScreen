
package com.loginscreen.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.loginscreen.dao.implementations.UsuarioDaoJpaImpl;
import com.loginscreen.dao.interfaces.UsuarioDao;
import com.loginscreen.models.Usuario;
import com.loginscreen.utils.ServletAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/atualizarSenha"})
public class AtualizarSenhaServlet extends HttpServlet implements ServletAction {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.getSession().setAttribute("paginaDestino", "/WEB-INF/jsps/atualizarSenha.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String novaSenha = req.getParameter("novaSenha");
        String senhaRepetida = req.getParameter("novaSenhaRepetida");
        req.getSession().setAttribute("paginaDestino", "/");

        if (email == null || novaSenha == null || senhaRepetida == null || email.isEmpty() || novaSenha.isEmpty() || senhaRepetida.isEmpty()) {
            req.getSession().setAttribute("mensagem", "Preencha todos os campos!");
            req.getSession().setAttribute("paginaDestino", "/?action=AtualizarSenhaServlet");
        } else {
            try {
                UsuarioDao dao = new UsuarioDaoJpaImpl();
                Usuario usuario = dao.get(email);

                if (usuario == null) {
                    req.getSession().setAttribute("mensagem", "Email não cadastrado!");
                    req.getSession().setAttribute("paginaDestino", "/?action=AtualizarSenhaServlet");
                } else if (!novaSenha.equals(senhaRepetida)) {
                    req.getSession().setAttribute("mensagem", "As senhas não são compatíveis!");
                    req.getSession().setAttribute("paginaDestino", "/?action=AtualizarSenhaServlet");
                } else {
                    usuario.setSenha(novaSenha);
                    dao.update(email, usuario);
                    req.getSession().setAttribute("mensagem", "Senha atualizada com sucesso!");
                }
            } catch (SQLException e) {
                req.getSession().setAttribute("mensagem", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        return (String) req.getSession().getAttribute("paginaDestino");
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
        return (String) req.getSession().getAttribute("paginaDestino");
    }
}
