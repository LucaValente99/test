package test.com.luca.architecture.dbaccess;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.architecture.dbaccess.DBAccess;

class DBAccessTest {

	@Test
	void testConnection() {
		try {
			DBAccess.getConnection();
		} catch (ClassNotFoundException | DAOException | IOException exc) {
			exc.printStackTrace();
			fail("Errore nel tentativo di connessione: " + exc.getMessage());
		} finally {
			try {
				DBAccess.closeConnection();
			}catch(DAOException exc) {
				exc.printStackTrace();
				fail("Errore nella chiusura della connessione: " + exc.getMessage());
			}
		}
		
	}
}
