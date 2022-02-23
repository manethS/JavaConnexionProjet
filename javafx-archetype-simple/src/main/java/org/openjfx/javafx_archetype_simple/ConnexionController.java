package org.openjfx.javafx_archetype_simple;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import Model.Utilisateur;

public class ConnexionController {
	String login = "";
	String pwd = "";
	String role = "";
	@FXML private TextField txtLogin;
	@FXML private PasswordField txtMdp;
	@FXML private Label lblErreur;
	String[] tab;
	public static int index;

	private JSONParser parser = new JSONParser();
	Utilisateur user = new Utilisateur();
	protected static  String path = "src/main/resources/asset/jsonFile/userList.json";
	MdpoublieController mdpOublie = new MdpoublieController();
	AlertController alert = new AlertController();
	
	@FXML 
	private void initialize() throws ParseException {
		if (mdpOublie.pwdChanged) {
			passwordChanged();
		} else {
			txtLogin.setText("");
			txtMdp.setText("");
		}
	}

	@FXML
	private void switchToInscription() throws IOException {
		App.setRoot("inscription");
	}

	@FXML
	private void switchToMdpoublie() throws IOException {
		App.setRoot("mdpoublie");
	}

	@FXML
	/**
	 * Diriger vers la page d'utilisateur 
	 * soit de type Admin ou de type utilisateur simple
	 * en fonction du rôle enregistré pour l'utilisateur concerné
	 * @throws IOException
	 * @throws ParseException
	 */
	private void switchToCompte() throws IOException, ParseException {
		login = txtLogin.getText().trim();
		pwd = txtMdp.getText().trim();
		
		tab = checkConnection(login, pwd);

		if (tab[0].equalsIgnoreCase("true")) {
			if (tab[1].equalsIgnoreCase("admin")) {
				App.setRoot("compteadmin");
			} else {
				App.setRoot("compteuser");
			}
		} else {
			alert.msgUserNotExist();
		}
	}
	
	/**
	 * Auto rempli les champs si le mot de passe a été modifié
	 */
	public void passwordChanged() {
		if (mdpOublie.pwdChanged) {
			tab = mdpOublie.getInfo();
			txtLogin.setText(tab[0]);
			txtMdp.setText(tab[1]);
		}
		mdpOublie.pwdChanged = false;
	}

	/**
	 * 
	 * @param login
	 * @param mdp
	 * Verifier la connexion d'un utilisateur
	 * Verifier l'état des champs
	 * Lire un fichier JSON 
	 * Vérifier si les informations saisi existent 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String[] checkConnection(String login, String mdp) throws FileNotFoundException, IOException, ParseException { 
		String isExist = "false";
		String[] tab = new String[2];
		index = 0;

		Boolean isEmpty = checkFieldEmpty(login, mdp);

		if (isEmpty == false) {

			JsonFileController.readJsonFile();

			for (int i = 0; i < JsonFileController.users.size(); i++) {
				index++;
				if (login.equalsIgnoreCase(JsonFileController.users.get(i).getLogin().trim())) {
					if (mdp.equalsIgnoreCase(JsonFileController.users.get(i).getPassword())) {
						role = JsonFileController.users.get(i).getRole();
						isExist = "true";
						tab[0] = isExist;
						tab[1] = role;
						break;
					} else {
						isExist = "false"; 
						tab[0] = isExist;
					}
				}else {
					isExist = "false"; 
					tab[0] = isExist;
				}
			}
		}

		return tab; 
	}

	/**
	 * Vérifier les champs
	 * @param login
	 * @param mdp
	 */
	public boolean checkFieldEmpty(String login, String mdp) {
		Boolean isEmpty = false;
		if (login.isEmpty() || mdp.isEmpty()) {
			isEmpty = true;
		} 

		return isEmpty;
	}
	
	/**
	 * l'index correspond au user dans la liste (JSON)
	 * @return
	 */
	public static int getUserIndex() {
		return index;
	}
	
}
