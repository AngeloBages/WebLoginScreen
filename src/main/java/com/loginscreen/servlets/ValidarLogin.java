package com.loginscreen.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.loginscreen.dao.Dao;
import com.loginscreen.dao.UsuarioDaoImpl;
import com.loginscreen.models.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/validarLogin"})
public class ValidarLogin extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");

		if((email == null || senha == null) || (email.isEmpty() || senha.isEmpty())) {
			req.setAttribute("mensagem", "Preencha todos os campos!");
			req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
		}
		
		Dao<Usuario> dao = new UsuarioDaoImpl();
		Usuario usuario = null;
		
		try {
			usuario = dao.get(email);

		} catch (SQLException e) {
			req.setAttribute("mensagem", e.getMessage());
			req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
			e.printStackTrace();
		}
		
		if(usuario == null) {
			req.setAttribute("mensagem", "Email não cadastrado!");
			req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
			
		}else if(!senha.equals(usuario.getSenha())) {
			req.setAttribute("mensagem", "Senha incorreta!");
			req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
		}
		
		String userName = email.substring(0, (email.indexOf("@")));
		req.setAttribute("userName", userName);
		req.getRequestDispatcher("/WEB-INF/jsps/boasVindas.jsp").forward(req, resp);
		
	}
}
