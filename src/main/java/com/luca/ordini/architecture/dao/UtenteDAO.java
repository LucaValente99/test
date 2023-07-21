package com.luca.ordini.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.luca.ordini.businesscomponent.model.Utente;
import com.luca.ordini.businesscomponent.security.Algoritmo;

public class UtenteDAO implements GenericDAO<Utente>, DAOConstansts{
	private CachedRowSet rowSet;
	
	private UtenteDAO() throws DAOException{
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}
	
	public static UtenteDAO getFactory() throws DAOException {
		return new UtenteDAO();
	}
	
	@Override
	public void create(Connection conn, Utente entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_UTENTE);
			//Se non passassi la connection l'esecuzione lancerebbe un exc, non si sa su quale connessione eseguire il comando 
			rowSet.execute(conn); 
			rowSet.moveToInsertRow();
			rowSet.updateString(1, entity.getNome());
			rowSet.updateString(2, entity.getCognome());
			rowSet.updateString(3, entity.getIndirizzo());
			rowSet.updateString(4, entity.getCap());
			rowSet.updateDate(5, new java.sql.Date(entity.getNascita().getTime()));
			rowSet.updateString(6, entity.getUsername());
			rowSet.updateString(7, Algoritmo.convertiMD5(entity.getPassword()));
			rowSet.updateString(8, entity.getEmail());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges(); //Si riconnette al db e fa il commit delle operazioni
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public void update(Connection conn, Utente entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(UPDATE_UTENTE);
			ps.setString(1, entity.getNome());
			ps.setString(2, entity.getCognome());
			ps.setString(3, entity.getIndirizzo());
			ps.setString(4, entity.getCap());
			ps.setDate(5, new java.sql.Date(entity.getNascita().getTime()));
			ps.setString(6, Algoritmo.convertiMD5(entity.getPassword()));
			ps.setString(7, entity.getEmail());
			ps.setString(8, entity.getUsername());
			ps.execute();
			conn.commit();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public void delete(Connection conn, Utente entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_UTENTE);
			ps.setString(1, entity.getUsername());
			ps.execute();
			conn.commit();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public Utente getById(Connection conn, Utente entity) throws DAOException {
		PreparedStatement ps;
		Utente utente = null;
		
		try {
			ps = conn.prepareStatement(SELECT_UTENTE_BYID);
			ps.setString(1, entity.getUsername());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				utente = new Utente();
				utente.setNome(rs.getString(1));
				utente.setCognome(rs.getString(2));
				utente.setIndirizzo(rs.getString(3));
				utente.setCap(rs.getString(4));
				utente.setNascita(new java.util.Date(rs.getDate(5).getTime()));
				utente.setUsername(rs.getString(6));
				utente.setPassword(Algoritmo.convertiMD5(rs.getString(7)));
				utente.setEmail(rs.getString(8));
			}
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		
		return utente;
	}

	@Override
	public Utente[] getAll(Connection conn) throws DAOException {
		Utente[] utenti = null;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, //Scrollabile insensibile perchè dobbiamo solo leggere e non modificare
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_UTENTE);
			rs.last();
			utenti = new Utente[rs.getRow()];
			rs.beforeFirst();
			for(int i = 0; rs.next(); i++) {
				Utente utente = new Utente();
				utente.setNome(rs.getString(1));
				utente.setCognome(rs.getString(2));
				utente.setIndirizzo(rs.getString(3));
				utente.setCap(rs.getString(4));
				utente.setNascita(new java.util.Date(rs.getDate(5).getTime()));
				utente.setUsername(rs.getString(6));
				utente.setPassword(Algoritmo.convertiMD5(rs.getString(7)));
				utente.setEmail(rs.getString(8));
				utenti[i] = utente;
			}
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		return utenti;
	}
}
