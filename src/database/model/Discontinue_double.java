package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;

public class Discontinue_double {

	private ResultSet result;
	private String query = null;
	private Statement stm;
	private String HE_1,HE_2,HS_1,HS_2;

	public Discontinue_double() throws SQLException {
		stm = Database.connection.createStatement();
	}

	public ResultSet select(String carte) {

		query = "select HE_1 ,HE_2 ,HS_1 ,HS_2 from discontinue_double where carte ='" + carte + "'";

		try {

			result = stm.executeQuery(query);

		} catch (SQLException e) {
		}
		return result;
	}// Fin select

	public void ajouter(String carte, String HE_1, String HE_2, String HS_1, String HS_2) {

		query = "insert into discontinue_double (carte,HE_1,HE_2,HS_1,HS_2) values ('" + carte + "','" + HE_1 + "','"
				+ HE_2 + "','" + HS_1 + "','" + HS_2 + "')";

		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// Fin ajouter

	public void modifier(int id, String carte, String HE_1, String HE_2, String HS_1, String HS_2) throws SQLException {

		query = "update discontinue_double set carte = '" + carte + "', HE_1 = '" + HE_1 + "', HE_2 = '" + HE_2
				+ "', HS_1 = '" + HS_1 + "', HS_2 = '" + HS_2 + "' where id = " + id;

		stm.executeUpdate(query);

	}// Fin modifier

	public void modifier_heures(String carte, String HE_1, String HE_2, String HS_1, String HS_2){
		query = "update discontinue_double set HE_1 = '" + HE_1 + "', HE_2 = '" + HE_2
				+ "', HS_1 = '" + HS_1 + "', HS_2 = '" + HS_2 + "' where carte = '" + carte + "'";

		try {
			stm.executeUpdate(query);
			System.out.println("Modification réussit");
		} catch (SQLException e) {
			System.out.println("Modification échoué");
			e.printStackTrace();
		}
	}
	public void supprimer(String carte) {

		query = "delete from discontinue_double where Carte = '"+carte+"'";

		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// Fin supprimer

	public boolean verifier(String carte) {

		try {
			if (select(carte).next()) {
				
				HE_1 = result.getString("HE_1");
				HE_2 = result.getString("HE_2");
				HS_1 = result.getString("HS_1");
				HS_2 = result.getString("HS_2");
				
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getHE_1() {
		return HE_1;
	}

	public void setHE_1(String hE_1) {
		HE_1 = hE_1;
	}

	public String getHE_2() {
		return HE_2;
	}

	public void setHE_2(String hE_2) {
		HE_2 = hE_2;
	}

	public String getHS_1() {
		return HS_1;
	}

	public void setHS_1(String hS_1) {
		HS_1 = hS_1;
	}

	public String getHS_2() {
		return HS_2;
	}

	public void setHS_2(String hS_2) {
		HS_2 = hS_2;
	}
	
	
}
