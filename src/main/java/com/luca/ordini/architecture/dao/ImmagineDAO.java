package com.luca.ordini.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.luca.ordini.businesscomponent.model.Immagine;

public class ImmagineDAO extends AdapterDAO<Immagine> implements DAOConstansts{
		
	public static ImmagineDAO getFactory() throws DAOException {
		return new ImmagineDAO();
	}
	
	@Override
	public void delete(Connection conn, Immagine entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_IMMAGINE);
			ps.setLong(1, entity.getIdImmagine());
			ps.execute();
			conn.commit();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}
	
	@Override
	public Immagine getById(Connection conn, Immagine entity) throws DAOException {
		Immagine immagine = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECT_IMMAGINE_BYID);
			ps.setLong(1, entity.getIdImmagine());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				immagine = new Immagine();
				immagine.setIdImmagine(rs.getLong(1));
				immagine.setUrl(rs.getString(2));
				immagine.setUrl(rs.getString(3));
			}
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		return immagine;
	}
}
