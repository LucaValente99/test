package com.luca.ordini.businesscomponent;

import java.io.IOException;
import java.sql.Connection;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dao.ImmagineDAO;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.model.Immagine;

public class ImmagineBC {
	private Connection conn;
	private ImmagineDAO iDAO; //ArticoloDAO
	
	public ImmagineBC() throws ClassNotFoundException, DAOException, IOException {
		conn = DBAccess.getConnection();
		iDAO = ImmagineDAO.getFactory();
	}
	
	public Immagine findByID(Immagine immagine) throws DAOException{
		try {
			return iDAO.getById(conn, immagine);
		}finally {
			DBAccess.closeConnection(); 
		}
	}
	
	public void delete(Immagine immagine) throws DAOException{
		try {
			iDAO.delete(conn, immagine);
		}finally {
			DBAccess.closeConnection(); 
		}
	}
}
