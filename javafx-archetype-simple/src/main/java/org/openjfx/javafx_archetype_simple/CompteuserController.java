package org.openjfx.javafx_archetype_simple;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CompteuserController {
	@FXML private Label lblRole;
	@FXML private Label lblNom;
	@FXML private Label lblPrenom;
	@FXML private Label lblDateN;
	@FXML private Label lblEmail;
	@FXML private Label lblTel;
	@FXML private Label lblAdresse;
	@FXML private TextField txtPhoto;
	@FXML private Button btnDelete;
	@FXML public ImageView imgProfil;
	AlertController alert = new AlertController();
	File defautImg = new File("src/main/resources/asset/images/user.png");

	@FXML
	/**
	 * Déconnecter du compte
	 * Diriger vers la page de connexion
	 * @throws IOException
	 */
	private void switchToConnexion() throws IOException {
		App.setRoot("connexion");
	}

	@FXML
	/**
	 * Alimenter les zones
	 * Activer ou non l'option suppression de photo
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		init();
		if (!txtPhoto.getText().isEmpty()) {
			btnDelete.setDisable(false);
		} else {
			btnDelete.setDisable(true);
		}
	}

	@FXML
	/**
	 * Modifier la photo de profile
	 * @throws IOException
	 */
	private void updateImage() throws IOException {
		if (!txtPhoto.getText().isEmpty()) {
			btnDelete.setDisable(false);
		} else {
			alert.mgsUrlPhoto();
		}
	}

	@FXML
	/**
	 * Supprimer la photo de profile
	 * @throws IOException
	 */
	private void delImage() throws IOException {	
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Supprimer la photo de profile.");
		alert.setHeaderText(null);
		alert.setContentText("Voulez-vous supprimer votre photo de profile?");
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == ButtonType.OK) {
			txtPhoto.setText("");
			btnDelete.setDisable(true);
		}
	}

	/**
	 * Alimenter les labels avec les informations correspondant
	 * @throws ParseException
	 */
	private void init() throws ParseException {
		ConnexionController conIndex= new ConnexionController();
		int i = conIndex.getUserIndex() -1;
		String nom = JsonFileController.users.get(i).getNom();
		String prenom = JsonFileController.users.get(i).getPrenom();
		nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
		prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
		String date = JsonFileController.users.get(i).getDateBirth();
		String adresse = JsonFileController.users.get(i).getAddress();
		String email = JsonFileController.users.get(i).getEmail();
		String telephone = JsonFileController.users.get(i).getTel();
		String url = JsonFileController.users.get(i).getPhoto();
		File profile = new File(url);

		try {
			Image image = new Image(profile.toURI().toString(), 100, 100, false, false);
			imgProfil.setFitHeight(130);
			imgProfil.setFitWidth(100);
			imgProfil.setImage(image);
		}
		catch(Exception e) {
			Image image = new Image(defautImg.toURI().toString(), 100, 100, false, false);
			imgProfil.setFitHeight(130);
			imgProfil.setFitWidth(100);
			imgProfil.setImage(image);
		}

		lblRole.setText("User");
		lblNom.setText(nom);
		lblPrenom.setText(prenom);
		lblDateN.setText(date);
		lblAdresse.setText(adresse);
		lblEmail.setText(email);
		lblTel.setText(telephone);
	}
}