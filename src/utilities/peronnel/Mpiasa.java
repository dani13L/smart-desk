package utilities.peronnel;

public class Mpiasa {

	private String nom;
	private String prenom;
	private String carte;
	private String photo;
	private String eve;
	private int etat;
	
	public Mpiasa() {}

	public Mpiasa(String nom, String prenom, String carte,String photo,int etat,String eve) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.carte = carte;
		this.photo = photo;
		this.etat = etat;
		this.eve = eve;
	}

	
	public String getEve() {
		return eve;
	}

	public void setEve(String eve) {
		this.eve = eve;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCarte() {
		return carte;
	}

	public void setCarte(String carte) {
		this.carte = carte;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}
