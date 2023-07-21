package com.luca.ordini.businesscomponent.model;

import java.io.Serializable;

public class Immagine implements Serializable{
	private static final long serialVersionUID = -7621196494041778788L;
	
	private long idImmagine;
	private String url;
	private String descrizione;
	
	public long getIdImmagine() {
		return idImmagine;
	}
	public void setIdImmagine(long id_immagine) {
		this.idImmagine = id_immagine;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "Immagine [id_immagine=" + idImmagine + ", url=" + url + ", descrizione=" + descrizione + "]";
	}
}
