package test.com.luca.ordini.architecture.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dao.UtenteDAO;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.model.Utente;

@TestMethodOrder(OrderAnnotation.class)
class UtenteDAOTest {
	private static Utente utente;
	private static Connection conn;
	
	@BeforeAll //Eseguito prima di ogni test
	static void setUp() throws Exception {
		conn = DBAccess.getConnection();
		utente = new Utente();
		utente.setNome("Massimo");
		utente.setCognome("Bianchi");
		utente.setIndirizzo("Via Milano, 5");
		utente.setCap("20100");
		utente.setNascita(new GregorianCalendar(1990, 10, 1).getTime()); // ricordare che il mese è da considerarsi come uno in meno siccome l'indice sll'enumerazione parte da 0
		utente.setUsername("max");
		utente.setPassword("Pass01$");
		utente.setEmail("max@gmail.com");
	}
	
	@AfterAll //Eseguito dopo ogni test
	static void tearDown() throws Exception {
		try {
			UtenteDAO.getFactory().delete(conn, utente);
			DBAccess.closeConnection();
		}catch(DAOException exc){
			exc.printStackTrace();
			fail("Delete fallito: " + exc.getMessage());
		}
	}
	
	@Test
	@Order(1)
	void testCreate() {
		try {
			//Quando si fanno i test è importante usare componenti che sono già stati testati!
			//In questo caso DBAccess è già stata testata quindi posso usarla
			UtenteDAO.getFactory().create(conn, utente);
		}catch(DAOException exc){
			exc.printStackTrace();
			fail("Create fallito: " + exc.getMessage());
		}
	}
	
	@Test
	@Order(2)
	void testUpdateGetById() {
		try {
			System.out.println("TEST UPDATE:");
			//utente pre aggiornamento
			System.out.println("- Utente prima dell'aggiornamento -\n" + utente);

			utente = new Utente();
			utente.setNome("Luca");
			utente.setCognome("Valente");
			utente.setIndirizzo("Via Roma, 23");
			utente.setCap("20040");
			utente.setNascita(new GregorianCalendar(1996, 9, 5).getTime()); // ricordare che il mese è da considerarsi come uno in meno siccome l'indice sll'enumerazione parte da 0
			utente.setUsername("max"); //Questo deve rimanere uguale siccome è la chiave primaria
			utente.setPassword("Pass02$");
			utente.setEmail("luca@gmail.com");
			
			UtenteDAO.getFactory().update(conn, utente);
			Utente uRecuperato = UtenteDAO.getFactory().getById(conn, utente);
			
			System.out.println("- Utente dopo l'aggiornamento -\n" + uRecuperato.toString());
			
		}catch(DAOException exc){
			exc.printStackTrace();
			fail("Update fallito: " + exc.getMessage());
		}
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		try {
			Utente[] utenti = UtenteDAO.getFactory().getAll(conn);
			assertNotNull(utenti); 
		}catch(DAOException exc) {
			exc.printStackTrace();
			fail("GetAll fallito: " + exc.getMessage());
		}		
	}
}
