package com.luca.ordini.businesscomponent.idgenerator;

import java.io.IOException;

import com.luca.ordini.architecture.dao.DAOException;

public interface IdGeneratorInterface {
	long getNextId() throws DAOException, ClassNotFoundException, IOException; 
}
