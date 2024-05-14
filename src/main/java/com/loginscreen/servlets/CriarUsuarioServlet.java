
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

@WebServlet(urlPatterns = {"/criarUsuario"})
public class CriarUsuarioServlet extends HttpServlet implements ServletAction {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("paginaDestino", "/WEB-INF/jsps/criarUsuario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        req.getSession().setAttribute("paginaDestino", "/");

        if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
            req.getSession().setAttribute("mensagem", "Preencha todos os campos!");
            req.getSession().setAttribute("paginaDestino", "/?action=CriarUsuarioServlet");
        } else {
            try {
            	UsuarioDao dao = new UsuarioDaoJpaImpl();
                
                if (dao.get(email) != null) {
                    req.getSession().setAttribute("mensagem", "Email já cadastrado!");
                    req.getSession().setAttribute("paginaDestino", "/?action=CriarUsuarioServlet");
                } else if (!email.matches("\\w+@[a-z]+\\.[a-z]{2,3}")){
                	req.getSession().setAttribute("mensagem", "O formato do Email é inválido!");
                	req.getSession().setAttribute("paginaDestino", "/?action=CriarUsuarioServlet");
                }else {
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    dao.insert(usuario);
                    req.getSession().setAttribute("mensagem", "Usuario criado com sucesso!");
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