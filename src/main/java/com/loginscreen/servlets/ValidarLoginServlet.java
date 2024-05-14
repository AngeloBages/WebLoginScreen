
package com.loginscreen.servlets;

import java.io.IOException;
import java.sql.SQLException;
import com.loginscreen.dao.implementations.UsuarioDaoImpl;
import com.loginscreen.dao.interfaces.UsuarioDao;
import com.loginscreen.models.Usuario;
import com.loginscreen.utils.ServletAction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/validarLogin"})
public class ValidarLoginServlet extends HttpServlet implements ServletAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        req.getSession().setAttribute("paginaDestino", "/");

        if((email == null || senha == null) || (email.isEmpty() || senha.isEmpty())) {
        	req.getSession().setAttribute("mensagem", "Preencha todos os campos!");
        }else {
			
        	UsuarioDao dao = new UsuarioDaoImpl();
        	Usuario usuario = null;
			
        	try {
        		usuario = dao.get(email);

        	} catch (SQLException e) {
        		req.getSession().setAttribute("mensagem", e.getMessage());
        		e.printStackTrace();
        	}
			
        	if(usuario == null) {
        		req.getSession().setAttribute("mensagem", "Email não cadastrado!");
				
        	}else if(!senha.equals(usuario.getSenha())) {
        		req.getSession().setAttribute("mensagem", "Senha incorreta!");
        	}else {
				
	        String userName = email.substring(0, (email.indexOf("@")));
	        req.getSession().setAttribute("userName", userName);
	        req.getSession().setAttribute("paginaDestino", "/?action=DisplayHomeServlet");
        	}
        }
    }

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
        return (String) req.getSession().getAttribute("paginaDestino");
    }
}
