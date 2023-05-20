package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;

public class Historique {

	private ResultSet resultset;
	private String query = "";
	private Statement stm;

	
	public Historique() {
		super();
		
		try {
			this.stm = Database.connection.createStatement();
		} catch (SQLException e) {
		}
	}
	
	public ResultSet select() throws SQLException {
	
		query = "select Nom, Prenom, Eve, Heure, Carte, Photo from historique";
		resultset = stm.executeQuery(query);
		
		
		return resultset;
	}//Fin select
	
	public void insert(String nom,String prenom,String eve,String heure,String carte,String photo) throws SQLException {
		
		query = "insert into historique (Nom,Prenom,Eve,Heure,Carte,Photo) values ('"
				+nom+"','"
				+prenom+"','"
				+eve+"','"
				+heure+"','"
				+carte+"','"
				+photo+"')";
		stm.executeUpdate(query);
		
	}// Fin insert

	public ResultSet dernierPointage(){
		
		query= "select Nom, Prenom, Eve, Heure, Photo, Carte from historique order by id desc";
		try {
			resultset = stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultset;
	}
	
}
