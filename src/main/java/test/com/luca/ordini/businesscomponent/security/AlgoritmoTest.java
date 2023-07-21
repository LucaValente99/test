package test.com.luca.ordini.businesscomponent.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.luca.ordini.businesscomponent.security.Algoritmo;

class AlgoritmoTest {

	@Test
	void testConversioneMD5() {
		String pass = Algoritmo.convertiMD5("Pass01$");
		assertNotNull(pass); //verifico che mi ritorni qualcosa
		System.out.println(pass);
	}

}
