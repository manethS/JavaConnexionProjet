package Model;

public class Utilisateur {
	private String prenom;
	private String nom;
	private String login;
	private String role;
	private String password;
	private String dateBirth;
	private String email;
	private String tel;
	private String address;
	private String photo;
	
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(String prenom, String nom, String role) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.role = role;
	}
	
	public Utilisateur(String nom, String prenom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public Utilisateur(String prenom, String nom, String login,  String role, String password, String dateBirth, String address,String email,String telephone, String photo) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.login = login;
		this.role = role;
		this.password = password;
		this.dateBirth = dateBirth;
		this.address = address;
		this.email = email;
		this.tel = telephone;
		this.photo = photo;
		
	}
	
	@Override
	public String toString() {
		return "Utilisateur [prenom=" + prenom + ", nom=" + nom + "]";
	}
	
	
}
