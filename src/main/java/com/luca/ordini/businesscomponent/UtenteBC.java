package com.luca.ordini.businesscomponent;

import java.io.IOException;
import java.sql.Connection;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dao.UtenteDAO;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.model.Utente;

public class UtenteBC {
	private Connection conn;
	private UtenteDAO uDAO; 
	
	public UtenteBC() throws ClassNotFoundException, DAOException, IOException {
		conn = DBAccess.getConnection();
		uDAO = UtenteDAO.getFactory();
	}
	
	public void create(Utente utente) throws DAOException, ClassNotFoundException, IOException {
		try {
			uDAO.create(conn, utente);
		} finally {
			DBAccess.closeConnection(); //Ricordarsi di chiudere la connessione perchè il 'try on resources' non agisce qui
		}
	}
}
