package org.openjfx.javafx_archetype_simple;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompteadminController {
	@FXML private Label lblNom;
	@FXML private Label lblPrenom;
	@FXML private TableView<UserList> userList;
	@FXML public TableColumn<UserList, String> colNom;
	@FXML public TableColumn<UserList, String> colPrenom;
	@FXML public TableColumn<UserList, String> colRole;
	@FXML public TableColumn<UserList, String> colEmail;
	@FXML public TableColumn<UserList, String> colTel;

	@FXML
	private void switchToConnexion() throws IOException {
		App.setRoot("connexion");
	}
	
	@FXML
	/**
	 * Alimenter les zones	@FXML private Label lblRole;
	@FXML private Label lblNom;
	@FXML private Label lblPrenom;
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		init();
		
	}
	
	ObservableList<UserList> getUserList(){
		ObservableList<UserList> users = FXCollections.observableArrayList();
		
		for (int i = 0; i < JsonFileController.users.size(); i++) {
			String nom = JsonFileController.users.get(i).getNom();
			String prenom = JsonFileController.users.get(i).getPrenom();
			String role = JsonFileController.users.get(i).getRole();
			String email = JsonFileController.users.get(i).getEmail();
			String telephone = JsonFileController.users.get(i).getTel();
			nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
			prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
			
			users.add(new UserList(prenom,nom,role, email,telephone));
		}
		return users;
		
	}
	
	/**
	 * Alimenter les colonnes de la table avec les informations correspondant
	 * @throws ParseException
	 */
	private void init() throws ParseException {
		ConnexionController conIndex= new ConnexionController();
		int i = conIndex.getUserIndex() -1;
		String nom = JsonFileController.users.get(i).getNom();
		String prenom = JsonFileController.users.get(i).getPrenom();
		nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
		prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
		lblNom.setText(nom);
		lblPrenom.setText(prenom);
		
		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		userList.setItems(getUserList());
	}
}