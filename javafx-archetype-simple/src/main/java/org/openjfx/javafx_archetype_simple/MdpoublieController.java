package org.openjfx.javafx_archetype_simple;

import java.io.IOException;
import java.util.Random;

import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MdpoublieController {
	@FXML private TextField txtId;
	AlertController alert = new AlertController();
	PasswordController pwd = new PasswordController();
	static Boolean pwdChanged = false;
	public static String[] tab;
	
	@FXML
	/**
	 * Affichier le nouveau mot de passe 
	 * Ensuite diriger vers la page de connexion
	 * @throws IOException
	 * @throws ParseException 
	 */
	private void switchToConnexion() throws IOException, ParseException {
		Boolean isEmpty = checkFieldEmpty(txtId.getText());
		if (isEmpty) {
			alert.msgLoginNeeded();
		} else {
			JsonFileController.readJsonFile();

			for (int i = 0; i < JsonFileController.users.size(); i++) {
				if (txtId.getText().trim().equals(JsonFileController.users.get(i).getLogin())) {
					tab = getPassword();
					String newPassword = tab[1];
					alert.msgNewPasswordGenerated(newPassword);
					pwdChanged = true;
					break;
				} 
			}
			
			if (!pwdChanged) {
				alert.msgUserNotFound();
			} else {
				App.setRoot("connexion");
			}
		}
		
	}
	
	@FXML
	private void retour() throws IOException {
		App.setRoot("connexion");
	}
	
	/**
	 * Générer un nouveau mot de passe
	 * @return
	 */
	public String[] getPassword() {
		String login = txtId.getText();
		tab = new String[2];
		String newPassword = pwd.generatePassword();
		tab[0] = login;
		tab[1] = newPassword;
		return tab;
	}
	
	/**
	 * Alimenter les zones dans la page connexion
	 * @return
	 */
	public String[] getInfo() {
		String[] sTab = tab;
		return sTab;
	}
	
	/**
	 * Vérifier si le champ est vide
	 * @param login
	 * @return
	 */
	private boolean checkFieldEmpty(String login) {
		Boolean isEmpty = false;
		if (login.isEmpty()) {
			isEmpty = true;
		} 
		
		return isEmpty;
	}

}