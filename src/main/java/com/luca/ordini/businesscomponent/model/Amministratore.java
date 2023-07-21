package com.luca.ordini.businesscomponent.model;

import java.io.Serializable;

public class Amministratore implements Serializable{
	private static final long serialVersionUID = 8334560121884876849L;
	
	private String username;
	private String password;
	private String email;
	
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
		return "Amministratore [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
}
