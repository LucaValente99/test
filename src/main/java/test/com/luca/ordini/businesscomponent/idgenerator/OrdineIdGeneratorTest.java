package test.com.luca.ordini.businesscomponent.idgenerator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.luca.ordini.architecture.dao.DAOException;
import com.luca.ordini.businesscomponent.idgenerator.OrdineIdGenerator;

class OrdineIdGeneratorTest {

	@Test
	void testGetNextId() {
		try {
			OrdineIdGenerator idGen = OrdineIdGenerator.getInstance();
			//verifico prima di tutto se l'idGen sia nullo -> qui fallirà il test se fosse effettivamente nullo
			assertNotNull(idGen); 
			long id = idGen.getNextId();
			//verifico che l'id equivalga al valore successivo nella sequenza - 1
			//questo mi permette di capire se vengono generati valori in sequenza
			assertEquals(id, idGen.getNextId() - 1); 
		}catch(ClassNotFoundException | DAOException | IOException exc){
			exc.printStackTrace();
			fail("Motivo: " + exc.getMessage());
		}
	}

}
