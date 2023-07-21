package com.luca.ordini.businesscomponent;

import java.io.IOException;
import java.sql.Connection;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dao.OrdineArticoloDAO;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.model.OrdineArticolo;

public class OrdineArticoloBC {
	private Connection conn;
	private OrdineArticoloDAO oaDAO; 
	
	public OrdineArticoloBC() throws ClassNotFoundException, DAOException, IOException {
		conn = DBAccess.getConnection();
		oaDAO = OrdineArticoloDAO.getFactory();
	}
	
	public void create(OrdineArticolo oa) throws DAOException, ClassNotFoundException, IOException {
		try {
			oaDAO.create(conn,oa);
		} finally {
			DBAccess.closeConnection(); //Ricordarsi di chiudere la connessione perchè il 'try on resources' non agisce qui
		}
	}
	
	public void delete(OrdineArticolo oa) throws DAOException{
		try {
			oaDAO.delete(conn, oa);
		}finally {
			DBAccess.closeConnection(); 
		}
	}
}
