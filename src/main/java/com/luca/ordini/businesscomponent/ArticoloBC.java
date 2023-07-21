package com.luca.ordini.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.luca.ordini.architecture.dao.ArticoloDAO;
import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.idgenerator.ArticoloIdGenerator;
import com.luca.ordini.businesscomponent.model.Articolo;

public class ArticoloBC {
	private Connection conn;
	private ArticoloDAO aDAO; //ArticoloDAO
	
	public ArticoloBC() throws ClassNotFoundException, DAOException, IOException {
		conn = DBAccess.getConnection();
		aDAO = ArticoloDAO.getFactory();
	}
	
	//L'articolo arriverà dal controller che l'avrà ricevuto dalla pagina JSP
	public void createOrUpdate(Articolo articolo) throws DAOException, ClassNotFoundException, IOException {
		try {
			if(articolo.getIdArticolo() > 0) { //Se maggiore di 0, l'articolo è già presente
				aDAO.update(conn, articolo);
			}else { 
				articolo.setIdArticolo(ArticoloIdGenerator.getInstance().getNextId());
				aDAO.create(conn, articolo);
			}
		} finally {
			DBAccess.closeConnection(); //Ricordarsi di chiudere la connessione perchè il 'try on resources' non agisce qui
		}
	}
	
	public Articolo findByID(Articolo articolo) throws DAOException{
		try {
			return aDAO.getById(conn, articolo);
		}finally {
			DBAccess.closeConnection(); 
		}
	}
	
	public Articolo[] getArticoli() throws DAOException{
		try {
			return aDAO.getAll(conn);
		}finally {
			DBAccess.closeConnection(); 
		}
	}
	
	public void delete(Articolo articolo) throws DAOException{
		try {
			aDAO.delete(conn, articolo);
		}finally {
			DBAccess.closeConnection(); 
		}
	}
	
	//Modulo di ricerca per marca e modello -> es. scrivo nella barra di ricerca del sito "Apple MacPro"
	public Set<Articolo> searchArticolo(String query) throws DAOException{
		try { //Restituisco gli articoli con un set ordinato per id degli articoli
			Set<Articolo> set = new TreeSet<Articolo>(new Comparator<Articolo>() {
				@Override
				public int compare(Articolo a1, Articolo a2) {
					return (int) (a1.getIdArticolo() - a2.getIdArticolo());
				}			
			});
			String[] criterioRicerca = query.toLowerCase().split(" ");
			for(Articolo a : getArticoli())
				for(String c : criterioRicerca)
					if(a.getMarca().toLowerCase().equals(c) 
				    || a.getModello().toLowerCase().equals(c))
						set.add(a);
			return set;
		}finally {
			DBAccess.closeConnection();
		}
	}
}
