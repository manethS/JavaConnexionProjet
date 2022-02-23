package org.openjfx.javafx_archetype_simple;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Tous les alertes
 * Information et Erreur
 * @author maneths
 *
 */
public class AlertController {
	public void msgUserNotExist() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Information incorrect");

		alert.setHeaderText(null);
		alert.setContentText("Votre login ou mot de passe est incorrect!");

		alert.showAndWait();
	}
	
	public void msgNewUser() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Déjà inscrit");

		alert.setHeaderText(null);
		alert.setContentText("Vous êtes déjà inscrit!");

		alert.showAndWait();
	}
	
	public void msgNewPasswordGenerated(String password) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Nouveau mot de passe");

		alert.setHeaderText(null);
		alert.setContentText("Votre nouveau mot de passe est : " + password + ".");

		alert.showAndWait();
	}
	
	public void msgLoginNeeded() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Obligatoire");

		alert.setHeaderText(null);
		alert.setContentText("Vous devez entrer votre login pour obtenir votre nouveau mot de passe!");

		alert.showAndWait();
	}
	
	public void msgUserNotFound() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Mauvais login");

		alert.setHeaderText(null);
		alert.setContentText("Ce login n'existe pas!");

		alert.showAndWait();
	}
	
	public void mgsUrlPhoto() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Pas de photo profile.");

		alert.setHeaderText(null);
		alert.setContentText("Vous devez saisir un url d'une photo pour votre profile!");

		alert.showAndWait();
	}
}
