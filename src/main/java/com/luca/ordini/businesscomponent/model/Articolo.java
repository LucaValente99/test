package com.luca.ordini.businesscomponent.model;

import java.io.Serializable;

public class Articolo implements Serializable{
	private static final long serialVersionUID = 3559151618816591040L;

	private long idArticolo;
	private String marca;
	private String modello;
	private double prezzo;
	
	public long getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(long idArticolo) {
		this.idArticolo = idArticolo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	@Override
	public String toString() {
		return "Articolo [idArticolo=" + idArticolo + ", marca=" + marca + ", modello=" + modello + ", prezzo=" + prezzo
				+ "]";
	}
}
