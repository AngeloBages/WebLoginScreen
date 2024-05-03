package com.loginscreen.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.loginscreen.dao.*;
import com.loginscreen.models.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/atualizarSenha"})
public class AtualizarSenhaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsps/atualizarSenha.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String novaSenha = req.getParameter("novaSenha");
		String senhaRepetida = req.getParameter("novaSenhaRepetida");
		
		if((email == null || novaSenha == null || senhaRepetida == null) || 
		  (email.isEmpty() || novaSenha.isEmpty() || senhaRepetida.isEmpty())) {
			req.setAttribute("mensagem", "Preencha todos os campos!");
			req.getRequestDispatcher("/WEB-INF/jsps/atualizarSenha.jsp").forward(req, resp);
		}
		
		try {
			
			Dao<Usuario> dao = new UsuarioDaoImpl();
			Usuario usuario = dao.get(email);
			
			if(usuario == null) {
				req.setAttribute("mensagem", "Email não cadastrado!");
				req.getRequestDispatcher("/WEB-INF/jsps/atualizarSenha.jsp").forward(req, resp);
				
			}else if(!novaSenha.equals(senhaRepetida)) {
				req.setAttribute("mensagem", "As senhas não são compatíveis!");
				req.getRequestDispatcher("/WEB-INF/jsps/atualizarSenha.jsp").forward(req, resp);
			}
			
			usuario.setSenha(novaSenha);
			
			dao.update(email, usuario);
			
		} catch (SQLException e) {
			req.setAttribute("mensagem", e.getMessage());
			req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
	}
}
