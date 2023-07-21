package com.luca.ordini.businesscomponent.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Algoritmo per criptare la password
public class Algoritmo {
	public static String convertiMD5(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");//componente di java security
			byte[] array = md.digest(password.getBytes(Charset.forName("UTF-8"))); //conversione da stringa ad array di byte
			StringBuffer buffer = new StringBuffer();
			/*
			Sale -> componente casuale inserito nella conversione per offuscare la conversione fatta (evita il reverse engineering)
			Posso mettere i caratteri che voglio - non duplicare numeri o caratteri speciali
			Più è complesso, più è vario, più sicura sarà la conversione e difficile fare reverse engineering
			*/
			String salt = "Rd6$1Np%a2JcX05fzL";
			for(int i = 0; i < array.length; i++) 
				//converto il char in salt in un esadecimale per renderlo ulteriormente sicuro
				buffer.append(String.format("%x", array[i]) + salt.toCharArray()[i]);
			return buffer.toString();
			
		} catch(NoSuchAlgorithmException exc) { //Se l'algoritmo non c'è verrà lanciata questa exc
			return null;
		}
	}
}
