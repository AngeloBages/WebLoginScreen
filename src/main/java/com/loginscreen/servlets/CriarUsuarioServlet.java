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

@WebServlet(urlPatterns= {"/criarUsuario"})
public class CriarUsuarioServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsps/criarUsuario.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		
		if((email == null || senha == null) || (email.isEmpty() || senha.isEmpty())) {
			req.setAttribute("mensagem", "Preencha todos os campos!");
			req.getRequestDispatcher("/WEB-INF/jsps/criarUsuario.jsp").forward(req, resp);
		}
		
		try {
			
			Dao<Usuario> dao = new UsuarioDaoImpl();
			
			Usuario usuario = dao.get(email);

			if(usuario != null) {
				req.setAttribute("mensagem", "O Email informado já está cadastrado!");
				req.getRequestDispatcher("/WEB-INF/jsps/criarUsuario.jsp").forward(req, resp);
			}
			
			usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(senha);
			
			if(!email.matches("\\w+@[a-z]+\\.[a-z]{2,3}")) {
				req.setAttribute("mensagem", "O formato do Email é inválido!");
				req.getRequestDispatcher("/WEB-INF/jsps/criarUsuario.jsp").forward(req, resp);
			}
			
			dao.insert(usuario);
			
		} catch (SQLException e) {
			req.setAttribute("mensagem", e.getMessage());
			req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
	}
}
