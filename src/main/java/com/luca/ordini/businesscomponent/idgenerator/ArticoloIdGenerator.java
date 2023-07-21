package com.luca.ordini.businesscomponent.idgenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.luca.ordini.architecture.dao.DAOConstansts;
import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dbaccess.DBAccess;

public class ArticoloIdGenerator implements IdGeneratorInterface, DAOConstansts{
	//utilizzo il pattern Singleton
	private static ArticoloIdGenerator idGen;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private ArticoloIdGenerator() {
		
	}
	
	public static ArticoloIdGenerator getInstance() {
		if(idGen == null)
			idGen = new ArticoloIdGenerator();
		return idGen;
	}

	@Override
	public long getNextId() throws DAOException, ClassNotFoundException, IOException {
		long id = 0;
		try {
			conn = DBAccess.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ARTICOLO_SEQ); //Recupero il prossimo valore della sequenza proggressivo
			rs.next();
			id = rs.getLong(1);
		}catch(SQLException sql) {
			throw new DAOException(sql);
		}
		return id;
	}

}
