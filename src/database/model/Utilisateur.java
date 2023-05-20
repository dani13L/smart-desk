package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;


public class Utilisateur {
	private ResultSet result;
	private String query = null;
	private Statement stm;
	
	public Utilisateur() throws SQLException {
		stm = Database.connection.createStatement();
	}
	//select
	public ResultSet select() throws SQLException {

		query = "select * from utilisateur";
		result = stm.executeQuery(query);
		return result;
	}
	//Ajout
	public void ajouter(String nom,String prenom,String userName, String pass,String question,String reponse) throws SQLException {
		
		query = "insert into utilisateur (Nom,Prenom,UserName,Mdp,Question,Reponse) values ('"
				+nom+"','"
				+prenom+"','"
				+userName+"','"
				+pass+"','"
				+question+"','"
				+reponse+"')";
		stm.executeUpdate(query);
	}
	
	//Modifier
	public void modifierPass(int id,String pass) throws SQLException {
					query= "update utilisateur  set Mdp = '"
							+pass+"' where id = "
							+id;
					stm.executeUpdate(query);
				}
	//Modifier username
	public void modifierUsername(String oldUsername,String username) throws SQLException {
		query= "update utilisateur  set UserName = '"
				+username+"' where UserName = '"
				+oldUsername+"'";
		stm.executeUpdate(query);
	}
	//Modifier réponse d'authentification
	public void modifierAns(String username,String reponse){
		query= "update utilisateur  set Reponse = '"
				+reponse+"' where UserName = '"
				+username+"'";
		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	


}
