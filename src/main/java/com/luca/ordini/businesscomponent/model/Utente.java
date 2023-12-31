package com.luca.ordini.businesscomponent.model;

import java.io.Serializable;
import java.util.Date;

public class Utente implements Serializable{
	private static final long serialVersionUID = 1707606883111910299L;
	
	private String nome;
	private String cognome;
	private String indirizzo;
	private String cap;
	private Date nascita;
	private String username;
	private String password;
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public Date getNascita() {
		return nascita;
	}
	public void setNascita(Date nascita) {
		this.nascita = nascita;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo + ", cap=" + cap
				+ ", nascita=" + nascita + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}
}
