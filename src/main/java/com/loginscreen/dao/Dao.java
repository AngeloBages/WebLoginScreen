package com.loginscreen.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	public void insert(T element) throws SQLException;
	public T get(Object id) throws SQLException;
	public List<T> getList() throws SQLException;
	public void update(Object id, T element) throws SQLException;
	public void delete(Object id) throws SQLException;
}
