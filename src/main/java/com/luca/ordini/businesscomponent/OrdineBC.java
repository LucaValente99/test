package com.luca.ordini.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dao.OrdineDAO;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.idgenerator.OrdineIdGenerator;
import com.luca.ordini.businesscomponent.model.Ordine;

public class OrdineBC {
	private Connection conn;
	private OrdineDAO oDAO; 
	
	public OrdineBC() throws ClassNotFoundException, DAOException, IOException {
		conn = DBAccess.getConnection();
		oDAO = OrdineDAO.getFactory();
	}
	
	public void create(Ordine ordine) throws DAOException, ClassNotFoundException, IOException {
		try {
			ordine.setIdOrdine(OrdineIdGenerator.getInstance().getNextId());
			ordine.setData(new Date());
			oDAO.create(conn,ordine);
		} finally {
			DBAccess.closeConnection(); //Ricordarsi di chiudere la connessione perchè il 'try on resources' non agisce qui
		}
	}
		
	public Ordine[] getOrdini() throws DAOException{
		try {
			return oDAO.getAll(conn);
		}finally {
			DBAccess.closeConnection(); 
		}
	}
	
	public void delete(Ordine ordine) throws DAOException{
		try {
			oDAO.delete(conn, ordine);
		}finally {
			DBAccess.closeConnection(); 
		}
	}
}
