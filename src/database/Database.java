package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	String URL= "jdbc:derby:smartdb;create=true";
	
	@SuppressWarnings("unused")
	public static Connection connection;
	
	
	//Constructeur
	public Database() {}
	
	
	//Pour se connecter � la base de donn�e
	public void ConnectBase() {
		
		try {
			
			connection = DriverManager.getConnection(URL);
			System.out.println("Connect� � la base");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	//Accesseurs 
	
	public Connection getConnection() {
		return connection;
	}
	
}
