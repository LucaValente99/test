package com.luca.ordini.architecture.dao;

import java.sql.SQLException;

public class DAOException extends SQLException{
	private static final long serialVersionUID = 38013177276236856L;
	
	private final static int ORA1017 = 1017;//verificata quando username e password (credenziali di accesso) sono sbagliate
	private final static int ORA17002 = 17002;//input output di oracle, non riesco a raggiungere il server/il server non risponde
	private final static int ORA00001 = 0;//violazione di vincolo
	
	public String message;

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	public DAOException(SQLException sql) {
		String chiave = "";
		if(sql != null) {
			switch(sql.getErrorCode()) {
			case ORA1017:
				chiave = "Credenziali di accesso errate. Invalid Username e Password";
				log(sql);
				break;
			case ORA17002:
				chiave = "Impossibile stabilire la connessione con il DB. I/O Error di Oracle";
				log(sql);
				break;
			case ORA00001:
				chiave = "Vincolo di tabella violato";
				log(sql);
				break;
			default:
				chiave = "Eccezione SQL non prevista";
				log(sql);
			}
		}
		message = chiave;
	}
	
	private void log(SQLException sql) {
		sql.printStackTrace();
		System.err.println("Motivo: " + sql.getMessage()); //così da poterlo recuperare nelle pagine d'errore
		System.err.println("Codice: " + sql.getErrorCode());
		
		//In quale stato si trovava l'applicazione nel momento in cui si è generata l'eccezione
		//Operazione eseugita nel punto sbagliato, verrà indicato il punto sbagliato e lo stato che ha generato il problema
		//Non si tratta mai di errori di sintassi
		System.err.println("Stato app: " + sql.getSQLState()); 
		System.err.println("Causa: " + sql.getCause());//motivo dell'eccezione 
		
	}
}
	