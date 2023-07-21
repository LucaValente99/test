package com.luca.ordini.businesscomponent.model;

import java.io.Serializable;

public class OrdineArticolo implements Serializable{
	private static final long serialVersionUID = -5852422119595522052L;
	
	private long idOrdine;
	private long idArticolo;
	private int qta;
	
	public long getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(long idOrdine) {
		this.idOrdine = idOrdine;
	}
	public long getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(long idArticolo) {
		this.idArticolo = idArticolo;
	}
	public int getQta() {
		return qta;
	}
	public void setQta(int qta) {
		this.qta = qta;
	}
	@Override
	public String toString() {
		return "OrdineArticolo [idOrdine=" + idOrdine + ", idArticolo=" + idArticolo + ", qta=" + qta + "]";
	}
	
}
