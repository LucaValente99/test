package com.luca.ordini.businesscomponent.facade;

import java.io.IOException;
import java.util.Set;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.businesscomponent.ArticoloBC;
import com.luca.ordini.businesscomponent.ImmagineBC;
import com.luca.ordini.businesscomponent.OrdineArticoloBC;
import com.luca.ordini.businesscomponent.OrdineBC;
import com.luca.ordini.businesscomponent.UtenteBC;
import com.luca.ordini.businesscomponent.model.Articolo;
import com.luca.ordini.businesscomponent.model.Immagine;
import com.luca.ordini.businesscomponent.model.Ordine;
import com.luca.ordini.businesscomponent.model.OrdineArticolo;
import com.luca.ordini.businesscomponent.model.Utente;

public class ClientFacade {
	private static ClientFacade cF;
	private UtenteBC uBC;
	private OrdineBC oBC;
	private ArticoloBC aBC;
	private OrdineArticoloBC oaBC;
	private ImmagineBC iBC;

	private ClientFacade() {
		
	}
	
	public static ClientFacade getInstance() {
		if (cF == null)
			cF = new ClientFacade();
		return cF;
	}
	
	public void create(Utente utente) throws ClassNotFoundException, DAOException, IOException {
		uBC = new UtenteBC();
		uBC.create(utente);
	}
	
	public void create(Ordine ordine) throws ClassNotFoundException, DAOException, IOException {
		oBC = new OrdineBC();
		oBC.create(ordine);
	}
	
	public void create(OrdineArticolo oa) throws ClassNotFoundException, DAOException, IOException {
		oaBC = new OrdineArticoloBC();
		oaBC.create(oa);
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
	
	public Immagine findImmagineByID(Immagine img) throws ClassNotFoundException, DAOException, IOException {
		iBC = new ImmagineBC();
		return iBC.findByID(img);
	}
}
