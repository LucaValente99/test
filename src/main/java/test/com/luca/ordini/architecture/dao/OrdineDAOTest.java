package test.com.luca.ordini.architecture.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dao.OrdineDAO;
import com.luca.ordini.architecture.dao.UtenteDAO;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.model.Ordine;
import com.luca.ordini.businesscomponent.model.Utente;

@TestMethodOrder(OrderAnnotation.class)
class OrdineDAOTest {
	private static Ordine ordine;
	private static Utente utente;
	private static Connection conn;
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
		
		//Importante inserire l'utente perchè si ha un dato correlato nella tabella Ordine
		utente = new Utente();
		utente.setNome("Massimo");
		utente.setCognome("Bianchi");
		utente.setIndirizzo("Via Milano, 5");
		utente.setCap("20100");
		utente.setNascita(new GregorianCalendar(1990, 10, 1).getTime()); // ricordare che il mese è da considerarsi come uno in meno siccome l'indice sll'enumerazione parte da 0
		utente.setUsername("max");
		utente.setPassword("Pass01$");
		utente.setEmail("max@gmail.com");
		
		ordine = new Ordine();
		ordine.setIdOrdine(1);
		ordine.setTotale(3000);
		ordine.setData(new Date());
		ordine.setUsername("max");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			OrdineDAO.getFactory().delete(conn, ordine); //Eliminare prima essendoci la FK
			UtenteDAO.getFactory().delete(conn, utente);
			DBAccess.closeConnection();
		}catch(DAOException exc){
			exc.printStackTrace();
			fail("Create fallito: " + exc.getMessage());
		}
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			UtenteDAO.getFactory().create(conn, utente);
			OrdineDAO.getFactory().create(conn, ordine);
		}catch(DAOException exc){
			exc.printStackTrace();
			fail("Delete fallito: " + exc.getMessage());
		}
	}
	
	@Test
	@Order(2)
	void updateGetById() {
		try {
			System.out.println("TEST UPDATE:");
			//utente pre aggiornamento
			System.out.println("- Ordine prima dell'aggiornamento -\n" + ordine);
	
			ordine = new Ordine();
			ordine.setIdOrdine(1);
			ordine.setTotale(2400);
			ordine.setData(new Date());
			ordine.setUsername("max");
			
			OrdineDAO.getFactory().update(conn, ordine);
			Ordine oRecuperato = OrdineDAO.getFactory().getById(conn, ordine);
			
			System.out.println("- Ordine dopo l'aggiornamento -\n" + oRecuperato.toString());
			
		}catch(DAOException exc){
			exc.printStackTrace();
			fail("Update fallito: " + exc.getMessage());
		}
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		try {
			Ordine[] ordini = OrdineDAO.getFactory().getAll(conn);
			assertNotNull(ordini); // verifico che venga passato qualcosa ad ordini
		}catch(DAOException exc) {
			exc.printStackTrace();
			fail("GetAll fallito: " + exc.getMessage());
		}		
	}

}
