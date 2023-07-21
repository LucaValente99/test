package com.luca.ordini.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.luca.ordini.businesscomponent.model.Ordine;

public class OrdineDAO implements GenericDAO<Ordine>, DAOConstansts{
	private CachedRowSet rowSet;
	
	private OrdineDAO() throws DAOException{
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}
	
	public static OrdineDAO getFactory() throws DAOException {
		return new OrdineDAO();
	}
	
	@Override
	public void create(Connection conn, Ordine entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_ORDINE);
			//Se non passassi la connection l'esecuzione lancerebbe un exc, non si sa su quale connessione eseguire il comando 
			rowSet.execute(conn); 
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getIdOrdine());
			rowSet.updateDouble(2, entity.getTotale());
			rowSet.updateDate(3, new java.sql.Date(entity.getData().getTime()));
			rowSet.updateString(4, entity.getUsername());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges(); //Si riconnette al db e fa il commit delle operazioni
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public void update(Connection conn, Ordine entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(UPDATE_ORDINE);
			ps.setDouble(1, entity.getTotale());
			ps.setDate(2, new java.sql.Date(entity.getData().getTime()));
			ps.setLong(3, entity.getIdOrdine());
			ps.execute();
			conn.commit();
			
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public void delete(Connection conn, Ordine entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_ORDINE);
			ps.setLong(1, entity.getIdOrdine());
			ps.execute();
			conn.commit();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	
	@Override
	public Ordine getById(Connection conn, Ordine entity) throws DAOException {
		PreparedStatement ps;
		Ordine ordine = null;
		try {
			ps = conn.prepareStatement(SELECT_ORDINE_BYID);
			ps.setLong(1, entity.getIdOrdine());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ordine = new Ordine();
				ordine.setIdOrdine(rs.getLong(1));
				ordine.setTotale(rs.getDouble(2));
				ordine.setData(new java.util.Date(rs.getDate(3).getTime()));
				ordine.setUsername(rs.getString(4));
			}
		}catch (SQLException exc) {
			throw new DAOException(exc);
		}
		
		return ordine;
	}
	 
	@Override
	public Ordine[] getAll(Connection conn) throws DAOException {
		Ordine[] ordini = null;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, //Scrollabile insensibile perchè dobbiamo solo leggere e non modificare
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_ORDINE);
			rs.last();
			ordini = new Ordine[rs.getRow()];
			rs.beforeFirst();
			for(int i = 0; rs.next(); i++) {
				Ordine ordine = new Ordine();
				ordine.setIdOrdine(rs.getLong(1));
				ordine.setTotale(rs.getLong(2));
				ordine.setData(new java.util.Date(rs.getDate(3).getTime()));
				ordine.setUsername(rs.getString(4));
				ordini[i] = ordine;
			}
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		
		return ordini;
	}

}
