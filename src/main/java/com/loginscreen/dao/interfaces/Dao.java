package com.loginscreen.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, K> {
	
	public void insert(T element) throws SQLException;
	
	public T get(K id) throws SQLException;
	
	public List<T> getAll() throws SQLException;
	
	public void update(K id, T element) throws SQLException;
	
	public void delete(K id) throws SQLException;
}
