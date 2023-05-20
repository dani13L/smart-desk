package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;

public class HeureContinue {

	private ResultSet result;
	private String query = null;
	private Statement stm;
	private String HE,HS;

	public HeureContinue() throws SQLException {
		stm = Database.connection.createStatement();
	}// Fin constructeur
	
	public ResultSet select(String carte) {
		
		query = "select HE,HS,id from heureContinue where carte ='"+carte+"'";
		
		try {
			
			result = stm.executeQuery(query);
		
		} catch (SQLException e) {
		}
		return result;
		
	}// Fin select
	
	public void ajouter(String carte, String HE,String HS) {
		
		query = "insert into heureContinue (carte,HE,HS) values ('"
				+carte+"','"
				+HE+"','"
				+HS+"')";
		
		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}// Fin ajouter

	public void modifier(int id,String carte,String HE,String HS){

		query = "update heureContinue set carte = '" + carte+ "', HE = '" + HE + "', HS = '" + HS + "' where id = " + id;

		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// Fin modifier

	public void modifier_Heures(String carte,String HE,String HS){
		query = "update heureContinue set HE = '" + HE + "', HS = '" + HS + "' where carte = '" + carte+ "'";
		try {
			stm.executeUpdate(query);
			System.out.println("Modification heure réussit");
		} catch (SQLException e) {
			System.out.println("Modification heure échoué");
			e.printStackTrace();
		}
	}

	public void supprimer(String carte) {

		query = "delete from heureContinue where Carte = '" + carte +"'";

		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// Fin supprimer

	/*Pour verifier si une carte existe dans la tables des heurecontinues
	 * Si oui, enregistrer tout de suite 
	 * les heures de d�but et fin de service
	 * 
	*/
	public boolean verifier(String carte) {
		
		try {
			if(select(carte).next()) {
				
				HE = result.getString("HE");
				HS = result.getString("HS");

				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public String getHE() {
		return HE;
	}

	public void setHE(String hE) {
		HE = hE;
	}

	public String getHS() {
		return HS;
	}

	public void setHS(String hS) {
		HS = hS;
	}

	
}
