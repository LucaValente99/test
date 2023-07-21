package com.luca.ordini.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.luca.ordini.businesscomponent.model.Articolo;

public class ArticoloDAO implements GenericDAO<Articolo>, DAOConstansts{
	private CachedRowSet rowSet;
	
	private ArticoloDAO() throws DAOException{
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}
	
	public static ArticoloDAO getFactory() throws DAOException {
		return new ArticoloDAO();
	}
	
	@Override
	public void create(Connection conn, Articolo entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_ARTICOLO);
			//Se non passassi la connection l'esecuzione lancerebbe un exc, non si sa su quale connessione eseguire il comando 
			rowSet.execute(conn); 
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getIdArticolo());
			rowSet.updateString(2, entity.getMarca());
			rowSet.updateString(3, entity.getModello());
			rowSet.updateDouble(4, entity.getPrezzo());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges(); //Si riconnette al db e fa il commit delle operazioni
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public void update(Connection conn, Articolo entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(UPDATE_ARTICOLO);
			ps.setString(1, entity.getMarca());
			ps.setString(2, entity.getModello());
			ps.setDouble(3, entity.getPrezzo());
			ps.setLong(4, entity.getIdArticolo());
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public void delete(Connection conn, Articolo entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_ARTICOLO);
			ps.setLong(1, entity.getIdArticolo());
			ps.execute();
			conn.commit();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public Articolo getById(Connection conn, Articolo entity) throws DAOException {
		Articolo articolo = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECT_ARTICOLO_BYID);
			ps.setLong(1, entity.getIdArticolo());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				articolo = new Articolo();
				articolo.setIdArticolo(rs.getLong(1));
				articolo.setMarca(rs.getString(2));
				articolo.setModello(rs.getString(3));
				articolo.setPrezzo(rs.getDouble(4));
			}
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		return articolo;
	}

	@Override
	public Articolo[] getAll(Connection conn) throws DAOException {
		Articolo[] articoli = null;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, //Scrollabile insensibile perchè dobbiamo solo leggere e non modificare
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_ARTICOLO);
			rs.last();
			articoli = new Articolo[rs.getRow()];
			rs.beforeFirst();
			for(int i = 0; rs.next(); i++) {
				Articolo articolo = new Articolo();
				articolo.setIdArticolo(rs.getLong(1));
				articolo.setMarca(rs.getString(2));
				articolo.setModello(rs.getString(3));
				articolo.setPrezzo(rs.getDouble(4));
				articoli[i] = articolo;
			}
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		return articoli;
	}

}
