package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;
import utilities.peronnel.*;

public class Personnel {

	private ResultSet result;
	private String query = null;
	private Statement stm;
	private Mpiasa mpiasa;
	
	public Personnel() throws SQLException {
		
		stm = Database.connection.createStatement();
		
		mpiasa = new Mpiasa();
	}

	//Methode pour selectionner 
	public String getLastElement() throws SQLException {
		String identite="";
		query="select Nom,Prenom from personne order by id desc";
		result = stm.executeQuery(query);
		if(result.next()) {
			identite = result.getString("Nom") + " " + result.getString("Prenom");
		}
		return identite;
	}
	
	
	public void selectPersonWithCard(String carte) {
		query = "select Nom, Prenom,Etat from personne where Carte ='"+carte+"'";
		try {
			
			result = stm.executeQuery(query);
			if(result.next()) {
				mpiasa.setNom(result.getString("Nom"));
				mpiasa.setPrenom(result.getString("Prenom"));
				mpiasa.setEtat(result.getInt("Etat"));
			}else {
				System.out.println("No such personne leka");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ? Select person by rfid card
	public ResultSet select(String carte) throws SQLException {
		
		query = "select Nom, Prenom,Photo,Etat from personne where Carte ='"+carte+"'";
		result = stm.executeQuery(query);

		return result;
	}

	// ? For showing personnel purpose
	public ResultSet select_for_list(String card){

		query = "select Nom, Prenom, Photo, Poste, id from personne where Carte ='"+card+"'";
		try {
			result = stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	// ? Select person by id
	public ResultSet select(int id) throws SQLException {
		
		query = "select Poste, CIN, Carte from personne where id ="+id;
		result = stm.executeQuery(query);

		return result;
	}

	// ? Select person by name
	public ResultSet selectPhoto(String carte){
		
		query = "select Photo from personne where Carte = '"+carte+"'";

		try {
			result = stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// * Get carte by ID
	public String select_card_by_id(int id) {
		String card = "";
		query = "select Poste, CIN, Carte from personne where id ="+id;
		try {
			result = stm.executeQuery(query);
			if(result.next()){
				card = result.getString("Carte");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return card;
	}
	// Méthode pour selectionner tout les elements de la table personne
	public ResultSet select() throws SQLException {

		query = "select Nom, Prenom, Poste, CIN, Carte, id,Heures, Photo from personne";
		result = stm.executeQuery(query);

		return result;

	}// Fin select

	public void ajouter(String nom,String prenom,String poste, long CIN, int heures,String carte, String photo,int etat){
		
		query = "insert into personne (Nom,Prenom,Poste,CIN,Heures,Carte,Photo,Etat) values ('"
				+nom+"','"
				+prenom+"','"
				+poste+"',"
				+CIN+","
				+heures+",'"
				+carte+"','"
				+photo+"',"
				+etat+")";
		
		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
		}
		
	}// Fin ajouter
	
	public void modifier(int id, String nom, String prenom, String poste,long CIN, int heures, String carte,String photo) throws SQLException {
		
		query= "update personne set Nom = '"
				+nom+"', Prenom = '"
				+prenom+"', Poste = '"
				+poste+"', CIN = "
				+CIN+", Heures = "
				+heures+", Carte = '"
				+carte+"', Photo = '"
				+photo+"' where id = "
				+id;
		
		stm.executeUpdate(query);
		
	}// Fin modifier
	
	public void supprimer(int id){
		
		query = "delete from personne where id = "+id;
		
		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//Fin supprimer

	public void updateState(String carte,int etat) {
		query = "update personne set Etat = "+ etat+" where Carte = '"+carte+"'";
		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public int retrieveState(String carte) {
		
		query = "select Etat from personne where Carte ='"+carte+"'";
		int u = 0;
		
		try {
			result = stm.executeQuery(query);
			if(result.next()) {
				u = result.getInt("Etat");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return u;
	}

	public Mpiasa getMpiasa() {
		return mpiasa;
	}

	public void setMpiasa(Mpiasa personnel) {
		this.mpiasa = personnel;
	}

}
