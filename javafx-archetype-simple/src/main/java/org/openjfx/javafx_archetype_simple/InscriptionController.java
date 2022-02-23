package org.openjfx.javafx_archetype_simple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import Model.Utilisateur;

/**
 * En cours
 * @author maneths
 *
 */
public class InscriptionController{
	String nom = "";
	String prenom = "";
	String role = "";
	String dateBirth = "";
	String email = "";
	String tel = "";
	String address = "";
	String login = "";
	String photo = "";
	@FXML private TextField txtNom;
	@FXML private TextField txtPrenom;
	@FXML private ComboBox cbxRole;
	@FXML private DatePicker dateDdn;
	@FXML private TextField txtEmail;
	@FXML private TextField txtAdresse;
	@FXML private TextField txtTel;
	@FXML private TextField txtCheminImage;
	@FXML private Button AjouterImgBtn;

	AlertController alert = new AlertController();
	PasswordController pwd = new PasswordController();

	@FXML
	private void switchToConnexion() throws IOException {
		App.setRoot("connexion");
	}

	@FXML
	private void switchToCompte() throws IOException, ParseException {
		nom = txtNom.getText();
		prenom = txtPrenom.getText();
		role = cbxRole.getValue().toString();
		dateBirth = dateDdn.getValue().toString();
		email = txtEmail.getText();
		tel = txtTel.getText();
		address = txtAdresse.getText();  
		photo = txtCheminImage.getText();

		String roleUser = inscrire(nom, prenom, role, dateBirth, email, tel, address, photo);

		if (roleUser.equalsIgnoreCase("Admin")) {
			App.setRoot("compteadmin");
		} else {
			App.setRoot("compteuser");
		}
	}

	@FXML
	private void modifierImage() throws IOException {
		if (!txtCheminImage.getText().isEmpty()) {
			AjouterImgBtn.setDisable(false);
		} else {
			AjouterImgBtn.setDisable(true);
		}
	}

	@FXML
	private void comboBox() throws IOException {
		cbxRole = new ComboBox();
		cbxRole.getItems().add("Choice 1");
		cbxRole.getItems().add("Choice 2");
		cbxRole.getItems().add("Choice 3");
		cbxRole.setValue("A");
		System.out.println(cbxRole.getValue());
	}

	private String inscrire(String nom, String prenom, String role, String dateBirth, String email, String tel, String address, String photo) throws IOException, ParseException {
		String password = "1234";
		String login = prenom + "." + nom;
		Boolean isEmpty = checkFleidEmpty(nom, prenom, role, dateBirth, email, tel, address, photo);
		Boolean isExist = checkInscription(login, role, dateBirth, address, email, tel);

		if (isEmpty == false) {
			if (isExist == false) {
				String paswword = pwd.generatePassword();
				alert.msgNewPasswordGenerated(password);
				Utilisateur user = new Utilisateur(prenom, nom, login, role, password, dateBirth, address, email, tel, photo);
				JsonFileController.users.add(user);
				JsonFileController.writeJsonFile();
			}
		}

		return role;
	}

	private boolean checkInscription(String login, String role, String dateBirth, String email, String tel, String address) throws FileNotFoundException, IOException, ParseException {
		boolean isExist = false;
		JsonFileController.readJsonFile();

		for (int i = 0; i < JsonFileController.users.size(); i++) {
			if (login.equals(JsonFileController.users.get(i).getLogin())) {
				isExist = true;
				break;
			}
		}

		if (isExist) {
			alert.msgNewUser();
		}
		return isExist;
	}

	private Boolean checkFleidEmpty(String nom, String prenom, String role, String dateBirth, String email, String tel, String address, String photo) {
		Boolean isEmpty = false;
		if (nom.isEmpty() || prenom.isEmpty() || role.isEmpty() || dateBirth.isEmpty() || email.isEmpty() || tel.isEmpty() || address.isEmpty()) {
			isEmpty = true;
		} 

		if (photo.isEmpty()) {
			//image par défaut
			photo = "src/main/resources/asset/images/user.png"; 
		}

		return isEmpty;
	}

	private String slogin(String nom, String prenom) {
		String outLogin = "";

		outLogin = prenom + "." + nom;

		return outLogin;
	}

	private String sdateBirth(String dateBirth) throws java.text.ParseException {
		String outDateBirth = "";

		final String inFormat = "yyyy-MM-dd";
		final String outFormat = "dd-MM-yyyy";

		SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
		Date d = sdf.parse(dateBirth);
		sdf.applyPattern(outFormat);
		outDateBirth = sdf.format(d);

		return outDateBirth;
	}

	private Boolean checkEmail(String email) {
		Boolean isCorrect = false;
		String regex = "^(.+)@(.+)$";  
		//Compile regular expression to get the pattern  
		Pattern pattern = Pattern.compile(regex);  
		//Create instance of matcher   
		Matcher matcher = pattern.matcher(email);  
		if (matcher.matches() == true) {
			isCorrect = true;
		}
		 
		return isCorrect;
	}

	private Boolean checkTel(String tel) {
		Boolean isCorrect = false;

		return isCorrect;
	}

	private Boolean checkAddress(String address) {
		Boolean isCorrect = false;

		return isCorrect;
	}

	private Boolean checkPhoto(String photo) {
		Boolean isCorrect = false;

		return isCorrect;
	}

}