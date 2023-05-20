package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;

public class Nivoaka {

	private ResultSet result;
	private String query = null;
	private Statement stm;
	private String date = "";
	private String time = "";

	public Nivoaka() throws SQLException {
		super();
		this.stm = Database.connection.createStatement();
	}

	/*
	 * Entina mi-verifier hoe sao dia efa ni-passer badge tao anatiny 30 minitra
	 * farany
	 */
	public ResultSet select() throws SQLException {
		
		query = "select Nom,Prenom,Depart,id,Carte,Date from nivoaka";
		
		result = stm.executeQuery(query);
		
		
		return result;
	}

	// ? Select last person 
	public ResultSet selectLastElement(){
		query = "select Nom,Prenom,Depart,id,Carte from nivoaka order by id desc";
		try {
			result = stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String selectLastPointage(String carte,String choice) {
		query = "select Depart,Date from nivoaka where Carte = '" + carte + "' order by id desc";

		try {

			result = stm.executeQuery(query);
			if (result.next()) {

				date = result.getString("Date");
				time = result.getString("Depart");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (choice.equalsIgnoreCase("date")) {
			return date;
		} else if (choice.equalsIgnoreCase("time")) {
			return time;
		}
		return "";

	}

	public void insert(String nom, String prenom, String carte, String depart, String date) {

		query = "insert into nivoaka (Nom,Prenom,Carte,Depart,Date) values('" + nom + "','" + prenom + "','" + carte
				+ "','" + depart + "','" + date + "')";

		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
