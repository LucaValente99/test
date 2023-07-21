package com.luca.ordini.architecture.dao;

public interface DAOConstansts {
	/* -- Client --*/
	String SELECT_UTENTE = "Select * from utente";
	String SELECT_ORDINE = "Select * from ordine";
	String SELECT_ARTICOLO = "Select * from articolo";
	String SELECT_ORDINE_ARTICOLO = "Select * from ordine_articolo";
	String SELECT_IMMAGINE = "Select * from immagine";
	
	/* -- Utente --*/
	String SELECT_USERPASS = "Select password from utente where username = ?"; //pwd dall'utente con filtro su username	
	String DELETE_UTENTE = "Delete from utente where username = ?"; //Eliminazione account utente
	String UPDATE_UTENTE = "Update utente set nome = ?, cognome = ?, indirizzo = ?, cap = ?, nascita = ?, password = ?, email = ? where username = ?";
	String SELECT_UTENTE_BYID = "Select * from utente where username = ?";
	
	/* -- Sequence --*/
	String SELECT_ORDINE_SEQ = "Select ordine_seq.nextval from dual";
	String SELECT_ARTICOLO_SEQ = "Select articolo_seq.nextval from dual";	
	
	/* -- Admin --*/
	String SELECT_ADMIN = "Select * from amministratore";
	String SELECT_ADMINPASS = "Select password from amministratore where username = ?";
	String SELECT_REPORT = "Select * from report";
	
	/* -- Articolo --*/
	String UPDATE_ARTICOLO = "Update articolo set marca = ?, modello = ?, prezzo = ? where id_articolo = ?";
	String DELETE_ARTICOLO = "Delete from articolo where id_articolo = ?";
	String SELECT_ARTICOLO_BYID = "Select * from articolo where id_articolo = ?";
	
	/* -- Immagine --*/
	String SELECT_IMMAGINE_BYID = "Select * from immagine where id_immagine = ?";
	String DELETE_IMMAGINE = "Delete from immagine where id_immagine = ?";
	
	/* -- Ordine --*/
	String DELETE_ORDINE = "Delete from ordine where id_ordine = ?";
	String UPDATE_ORDINE = "Update ordine set totale = ?, data = ? where id_ordine = ?";
	String SELECT_ORDINE_BYID = "Select * from ordine where id_ordine = ?";
	
	/* -- Ordine_Articolo --*/
	String DELETE_ORDINE_ARTICOLO = "Delete from ordine_articolo where id_ordine = ?";
	
	
}
