package test.com.luca.ordini.architecture.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dao.OrdineArticoloDAO;
import com.luca.ordini.architecture.dao.OrdineDAO;
import com.luca.ordini.architecture.dao.UtenteDAO;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.model.Ordine;
import com.luca.ordini.businesscomponent.model.OrdineArticolo;
import com.luca.ordini.businesscomponent.model.Utente;

class OrdineArticoloDAOTest {
	private static Utente utente;
	private static Ordine ordine;
	private static OrdineArticolo oa;
	private static Connection conn;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
		
		ordine = new Ordine();
		ordine.setIdOrdine(1);
		ordine.setTotale(3000);
		ordine.setData(new Date());
		ordine.setUsername("max");
		
		oa = new OrdineArticolo();
		oa.setIdOrdine(1);
		oa.setIdArticolo(3); //Prendo l'id di uno degli articoli già inseriti
		oa.setQta(4);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		UtenteDAO.getFactory().delete(conn, utente);
		OrdineArticoloDAO.getFactory().delete(conn, oa);
	}

	@Test
	void testCreate() {
		try {
			UtenteDAO.getFactory().create(conn, utente);
			System.out.println("ok");
			OrdineDAO.getFactory().create(conn, ordine);
			System.out.println("ok");
			OrdineArticoloDAO.getFactory().create(conn, oa);
			System.out.println("ok");
		}catch(DAOException exc){
			exc.printStackTrace();
			fail("Delete fallito: " + exc.getMessage());
		}
	}

}
