package com.loginscreen.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.loginscreen.dao.interfaces.UsuarioDao;
import com.loginscreen.models.Usuario;
import com.loginscreen.utils.ConnectionFactory;

public class UsuarioDaoImpl implements UsuarioDao{
	
	Connection connection;
	
	public UsuarioDaoImpl(){
		connection = ConnectionFactory.getConnection();
	}

	@Override
	public void insert(Usuario usuario) throws SQLException {
	    String sql = "INSERT INTO usuarios (email,senha) VALUES (?,?)";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, usuario.getEmail());
			stm.setString(2, usuario.getSenha());
			
			stm.execute();
		};
	}

	@Override
	public Usuario get(String email) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE email = ?";
		
		Usuario usuario = null;
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
			}
		}
		
		return usuario;
	}

	@Override
	public List<Usuario> getAll() throws SQLException {
		String sql = "SELECT * FROM usuarios";
		
		List<Usuario> lista = new ArrayList<>();

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				lista.add(usuario);
			}
		}
		return lista;
	}

	@Override
	public void update(String email, Usuario usuario) throws SQLException {
		String sql = "UPDATE usuarios SET senha = ? WHERE email = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setString(1, usuario.getSenha());
			stm.setString(2, usuario.getEmail());
			
			stm.execute();
		}
		
	}

	@Override
	public void delete(String email) throws SQLException {
		String sql = "DELETE FROM usuarios WHERE email = ?";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			
			stm.setString(1, (String) email);
			stm.execute();
		}
	}

}
