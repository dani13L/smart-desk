package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;

public class Niditra {

	private ResultSet result;
	private String query = null;
	private Statement stm;
	private String date = "";
	private String time = "";

	public Niditra() throws SQLException {
		super();
		this.stm = Database.connection.createStatement();
	}
	public ResultSet select() throws SQLException {
		
		query = "select Nom,Prenom,Arrive,id,Carte,Date from niditra";
		
		result = stm.executeQuery(query);
		
		
		return result;
	}

	// ? ici

	public String selectLastPointage(String carte,String choice) {

		/*
		 * Ity code ity tokony manome ny pointage farany nataon'ny Carte voasafidy io,
		 * izany hoe fantininy aloha ny pointage rehetra nataon'io olona io dia ny
		 * farany amin'ilay izy no raisina.
		 */

		query = "select Arrive,Date from niditra where Carte = '" + carte + "' order by id desc";

		try {

			result = stm.executeQuery(query);
			if (result.next()) {

				date = result.getString("Date");
				time = result.getString("Arrive");
			}

		} catch (SQLException e) {
			
			
			e.printStackTrace();
			
		}
		if(choice.equalsIgnoreCase("date")) {
			return date;
		}else if(choice.equalsIgnoreCase("time")) {
			return time;
		}
		return "";
		
	}

	public void insert(String nom, String prenom, String carte, String arrive, String date) {

		query = "insert into niditra (Nom,Prenom,Carte,Arrive,Date) values('" + nom + "','" + prenom + "','" + carte
				+ "','" + arrive + "','" + date + "')";

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
