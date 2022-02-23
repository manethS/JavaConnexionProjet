package org.openjfx.javafx_archetype_simple;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

class testConnexionController {

	@Test
	void testGetUserIndex() {
		ConnexionController conCont = new ConnexionController();
		assertNotNull(conCont);
	}
	
	@Test
	void testCheckFieldEmpty() {
		ConnexionController conCont = new ConnexionController();
		String login = "ken.adam";
		String mdp = "654321";
		assertNotNull(conCont.checkFieldEmpty(login, mdp));
	}
	
	@Test
	void testCheckConnexion() throws FileNotFoundException, IOException, ParseException {
		ConnexionController conCont = new ConnexionController();
		String login = "ken.adam";
		String mdp = "654321";
		String[] tab = conCont.checkConnection(login, mdp);
		System.out.println("User exists = " + tab[0] + " and his role = " + tab[1]);
	}
}
