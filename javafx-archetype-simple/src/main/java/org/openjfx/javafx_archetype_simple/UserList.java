package org.openjfx.javafx_archetype_simple;

public class UserList {
	private String prenom;
	private String nom;
	private String role;
	private String email;
	private String tel;

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public UserList(String prenom, String nom, String role, String email, String tel) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.role = role;
		this.email = email;
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Utilisateur [prenom=" + prenom + ", nom=" + nom + "]";
	}

}
