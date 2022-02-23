package org.openjfx.javafx_archetype_simple;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Model.Utilisateur;

public class JsonFileController {
	protected static List<Utilisateur> users;
	protected static  String path = "src/main/resources/asset/jsonFile/userList.json";

	public static void main(String[] args) throws org.json.simple.parser.ParseException, ParseException {
	}

	/**
	 * Lire le ficier JSON
	 * @throws org.json.simple.parser.ParseException
	 * @throws ParseException
	 */
	public static void readJsonFile() throws org.json.simple.parser.ParseException, ParseException {

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(path))
		{
			Object obj = jsonParser.parse(reader);
			JSONArray userList = (JSONArray) obj;
			users = new ArrayList<Utilisateur>();
			userList.forEach( user -> parseUserInfo( (JSONObject) user ) );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};		 
	}

	/**
	 * Ecrire le fichier JSON à partir d'une liste
	 */
	public static void writeJsonFile() {

		JSONArray userList = new JSONArray();

		for (int i = 0; i < users.size(); i++) {
			JSONObject userInfo = new JSONObject();
			userInfo.put("nom", users.get(i).getNom());
			userInfo.put("prenom", users.get(i).getPrenom());
			userInfo.put("login", users.get(i).getLogin());
			userInfo.put("role", users.get(i).getRole());
			userInfo.put("password", users.get(i).getPassword());
			userInfo.put("dateBirth", users.get(i).getDateBirth());
			userInfo.put("address", users.get(i).getAddress());
			userInfo.put("email", users.get(i).getEmail());
			userInfo.put("tel", users.get(i).getTel());
			userInfo.put("photo", users.get(i).getPhoto());

			JSONObject userObject = new JSONObject(); 
			userObject.put("user", userInfo);
			userList.add(userObject);

		}

		try (FileWriter file = new FileWriter(path)) {	            
			file.write(userList.toJSONString()); 
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Récupérer les données à partir du fichier JSON
	 * @param user
	 */
	private static void parseUserInfo(JSONObject user) 
	{
		JSONObject userObject = (JSONObject) user.get("user");

		String nom = (String) userObject.get("nom");    
		String prenom = (String) userObject.get("prenom"); 
		String login = (String)userObject.get("login");
		String role = (String) userObject.get("role");
		String password = (String) userObject.get("password");
		String dateBirth = (String) userObject.get("dateBirth");
		String address = (String) userObject.get("address");
		String email = (String) userObject.get("email");
		String tel = (String) userObject.get("tel");
		String photo = (String) userObject.get("photo");

		Utilisateur nUser = new Utilisateur( prenom, nom, login, role, password, dateBirth, address, email, tel, photo);
		users.add(nUser);

	}
}
