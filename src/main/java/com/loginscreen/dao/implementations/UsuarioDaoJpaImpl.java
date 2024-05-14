package com.loginscreen.dao.implementations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.loginscreen.dao.interfaces.UsuarioDao;
import com.loginscreen.models.Usuario;
import com.loginscreen.utils.JpaUtils;

import jakarta.persistence.EntityManager;

public class UsuarioDaoJpaImpl implements UsuarioDao{

	@Override
	public void insert(Usuario usuario) throws SQLException {
		
		EntityManager em = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			
		}finally{
			if(em != null) {
				em.close();
			}
		}
	}

	@Override
	public Usuario get(String email) throws SQLException {
		
		EntityManager em = null;
		Usuario usuario = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			usuario = em.find(Usuario.class, email);
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
		
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getAll() throws SQLException {

		EntityManager em = null;
		List<Usuario> lista = new ArrayList<>();
		
		try {
			em = JpaUtils.getEntityManager();
			
			lista = em.createQuery("from Usuario").getResultList();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}

		return lista;
	}

	@Override
	public void update(String email, Usuario usuario) throws SQLException {
		
		EntityManager em = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			usuario.setEmail(email);
		
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
	}

	@Override
	public void delete(String email) throws SQLException {

		EntityManager em = null;
		
		try {
			em = JpaUtils.getEntityManager();
			
			Usuario usuario = em.find(Usuario.class, email);
			
			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();
			
		}finally {
			if(em != null) {
				em.close();
			}
		}
	}
}
