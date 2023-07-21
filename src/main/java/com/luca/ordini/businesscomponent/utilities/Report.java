package com.luca.ordini.businesscomponent.utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.luca.ordini.architecture.dao.DAOConstansts;
import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dbaccess.DBAccess;

public class Report implements DAOConstansts{
	private Connection conn;
	
	public Report() throws ClassNotFoundException, DAOException, IOException {
		conn = DBAccess.getConnection();
	}
	
	// Con la lista sincronizzata evito problemi di concorrenza se più admin cercassero di recuperare il report
	public Vector<String[]> getReport() throws DAOException{
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, //sensibile sempre per gestire l'accesso multiplo da admin differenti
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_REPORT);
			ResultSetMetaData meta = rs.getMetaData();
			int colonne = meta.getColumnCount();
			
			Vector<String[]> dati = new Vector<String[]>();
			rs.beforeFirst();
			while(rs.next()) {
				String[] riga = new String[colonne];
				for(int i = 0; i < colonne; i++) 
					riga[i] = rs.getString(i+1);
				dati.add(riga);
			}
			return dati;
		}catch(SQLException sql) {
			throw new DAOException(sql);
		}
	}
}
