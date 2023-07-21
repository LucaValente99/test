package com.luca.ordini.businesscomponent.facade;

import java.io.IOException;
import java.util.Set;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.businesscomponent.ArticoloBC;
import com.luca.ordini.businesscomponent.ImmagineBC;
import com.luca.ordini.businesscomponent.OrdineBC;
import com.luca.ordini.businesscomponent.model.Articolo;
import com.luca.ordini.businesscomponent.model.Immagine;
import com.luca.ordini.businesscomponent.model.Ordine;

public class AdminFacade {
	private static AdminFacade cF;
	private OrdineBC oBC;
	private ArticoloBC aBC;
	private ImmagineBC iBC;

	private AdminFacade() {
		
	}
	
	public static AdminFacade getInstance() {
		if (cF == null)
			cF = new AdminFacade();
		return cF;
	}
	
	public Ordine[] getOrdini() throws ClassNotFoundException, DAOException, IOException {
		oBC = new OrdineBC();
		return oBC.getOrdini();
	} 
	
	public Articolo[] getArticoli() throws ClassNotFoundException, DAOException, IOException {
		aBC = new ArticoloBC();
		return aBC.getArticoli();
	}
	
	public Set<Articolo> searchArticolo(String query) throws ClassNotFoundException, DAOException, IOException {
		aBC = new ArticoloBC();
		return aBC.searchArticolo(query);
	}
		
	public Articolo findArticoloByID(Articolo articolo) throws ClassNotFoundException, DAOException, IOException {
		aBC = new ArticoloBC();
		return aBC.findByID(articolo);
	}
	
	public Articolo createOrUpdate(Articolo articolo) throws ClassNotFoundException, DAOException, IOException {
		aBC = new ArticoloBC();
		aBC.createOrUpdate(articolo);
		return articolo;
	}
	
	/*
	 * public void delete(Articolo articolo) { iBC = new ImmagineBC(); Immagine
	 * immagine = iBC.findByID()); }
	 */
	
	public Immagine findImmagineByID(Immagine img) throws ClassNotFoundException, DAOException, IOException {
		iBC = new ImmagineBC();
		return iBC.findByID(img);
	}
	
	public void delete(Ordine ordine) throws ClassNotFoundException, DAOException, IOException {
		oBC = new OrdineBC();
		oBC.delete(ordine);
	}
}
