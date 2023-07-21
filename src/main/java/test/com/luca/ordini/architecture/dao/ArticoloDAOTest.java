package test.com.luca.ordini.architecture.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.luca.ordini.architecture.dao.ArticoloDAO;
import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dbaccess.DBAccess;
import com.luca.ordini.businesscomponent.model.Articolo;

@TestMethodOrder(OrderAnnotation.class)
class ArticoloDAOTest {
	private static Connection conn;
	private static Articolo articolo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
		articolo = new Articolo();
		articolo.setIdArticolo(4);
		articolo.setMarca("Samsung");
		articolo.setModello("S20");
		articolo.setPrezzo(900);
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			ArticoloDAO.getFactory().delete(conn, articolo);
			System.out.println("Eliminato articolo");
			DBAccess.closeConnection();
		}catch(DAOException exc) {
			exc.printStackTrace();
			fail("Motivo: " + exc.getMessage());
		}
	}
	
	@Test
	@Order(1) // importante venga eseguito per primo essendo un create
	void test() {
		try {
			ArticoloDAO.getFactory().create(conn, articolo);
		}catch(DAOException exc) {
			exc.printStackTrace();
			fail("Create fallito: " + exc.getMessage());
		}
	}
	
	@Test
	@Order(2)
	void testUpdateGetByID() {
		try {
			articolo = new Articolo();
			articolo.setIdArticolo(4); //L'articolo deve essere lo stesso che ho inserito
			articolo.setMarca("HP");
			articolo.setModello("Envy");
			articolo.setPrezzo(1200);
			ArticoloDAO.getFactory().update(conn, articolo);
			System.out.println("Aggiornato articolo");
			Articolo aRecuperato = ArticoloDAO.getFactory().getById(conn, articolo);
			System.out.println("Articolo trovato: " + aRecuperato.toString());

		}catch(DAOException exc) {
			exc.printStackTrace();
			fail("Update fallito: " + exc.getMessage());
		}
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		try {
			Articolo[] articoli = ArticoloDAO.getFactory().getAll(conn);
			assertNotNull(articoli); // verifico che venga passato qualcosa ad articoli
		}catch(DAOException exc) {
			exc.printStackTrace();
			fail("GetAll fallito: " + exc.getMessage());
		}		
	}
}
