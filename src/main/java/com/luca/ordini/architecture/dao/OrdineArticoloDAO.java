package com.luca.ordini.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.luca.ordini.businesscomponent.model.OrdineArticolo;

public class OrdineArticoloDAO extends AdapterDAO<OrdineArticolo> implements DAOConstansts{
	private CachedRowSet rowSet;
	
	private OrdineArticoloDAO() throws DAOException{
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}
	
	public static OrdineArticoloDAO getFactory() throws DAOException {
		return new OrdineArticoloDAO();
	}
	
	@Override
	public void create(Connection conn, OrdineArticolo entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_ORDINE_ARTICOLO);
			//Se non passassi la connection l'esecuzione lancerebbe un exc, non si sa su quale connessione eseguire il comando 
			rowSet.execute(conn); 
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getIdOrdine());
			rowSet.updateLong(2, entity.getIdArticolo());
			rowSet.updateInt(3, entity.getQta());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges(); //Si riconnette al db e fa il commit delle operazioni
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

	@Override
	public void delete(Connection conn, OrdineArticolo entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_ORDINE_ARTICOLO);
			ps.setLong(1, entity.getIdOrdine());
			ps.execute();
			conn.commit();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}

}
