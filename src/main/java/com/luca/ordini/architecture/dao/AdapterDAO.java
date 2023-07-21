package com.luca.ordini.architecture.dao;

import java.sql.Connection;

public abstract class AdapterDAO<T> implements GenericDAO<T>{

	@Override
	public void create(Connection conn, T entity) throws DAOException {		
	}

	@Override
	public void update(Connection conn, T entity) throws DAOException {		
	}

	@Override
	public void delete(Connection conn, T entity) throws DAOException {		
	}

	@Override
	public T getById(Connection conn, T entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public T[] getAll(Connection conn) throws DAOException {
		throw new UnsupportedOperationException();
	}
	
}
