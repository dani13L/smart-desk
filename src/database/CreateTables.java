package database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateTables {

	private DatabaseMetaData metas;
	private ResultSet tables;
	private Statement stat;
	
	private ArrayList<String> T = new ArrayList<String>(Arrays.asList("personne","niditra","nivoaka","utilisateur","historique","IP","heureContinue","discontinue_double","discontinue_triple"));

	public CreateTables() {
		
		// Créer un table pour chaque liste
		for(String ble:T) {
			creerTable(ble);
		}
		
	}

	// Créer un table dans la base de donnée
	public void creerTable(String table) {

		String sqlReq = null;

		switch (table) {

		case "personne":

			sqlReq = "Create Table personne ("
					+"id int not null generated always as identity(Start with 1, Increment by 1),"
					+" Nom varchar(255),"
					+" Prenom varchar(255),"
					+" Poste varchar(128),"
					+" CIN bigint not null,"
					+" Heures int, "
					+" Carte varchar(255),"
					+" Photo varchar(500),"
					+" Etat int,"
					+" PRIMARY KEY (id))";
			
			break;
		case "niditra":
			sqlReq = "Create Table niditra ("
					+" id int not null generated always as identity,"
					+" Nom varchar(255),"
					+" Prenom varchar(255),"
					+" Carte varchar(20),"
					+" Arrive varchar(20),"
					+" Date varchar(250),"
					+" PRIMARY KEY (id))";
			break;
		case "nivoaka":
			sqlReq ="Create Table nivoaka ("
					+"id int not null generated always as identity,"
					+ "Nom varchar(255),"
					+" Prenom varchar(255),"
					+" Carte varchar(20),"
					+" Depart varchar(20),"
					+" Date varchar(250),"
					+" PRIMARY KEY (id))";
			break;
		case "utilisateur":
			sqlReq = "Create Table utilisateur ("
					+"id int not null generated always as identity,"
					+ "Nom varchar(255),"
					+" Prenom varchar(255),"
					+" UserName varchar(255),"
					+" Mdp varchar(100),"
					+" Question varchar(100),"
					+" Reponse varchar(100),"
					+" PRIMARY KEY (id))";
			break;
		case "historique":
			sqlReq =  "Create Table historique ("
					+"id int not null generated always as identity,"
					+ "Nom varchar(255),"
					+" Prenom varchar(255),"
					+" Eve varchar(30),"
					+" Heure varchar(20),"
					+" Carte varchar(12),"
					+" Photo varchar(255),"
					+" PRIMARY KEY (id))";
			break;
		case "IP":
			sqlReq =  "Create Table IP ("
					+"id int not null generated always as identity,"
					+ "IP varchar(255),"
					+" PRIMARY KEY (id))";
			break;
		case "heureContinue":
			sqlReq =  "Create Table heureContinue ("
					+"id int not null generated always as identity,"
					+ "carte varchar(12),"
					+ "HE varchar(20),"
					+ "HS varchar(20),"
					+" PRIMARY KEY (id))";
			break;
		case "discontinue_double":
			sqlReq =  "Create Table discontinue_double ("
					+"id int not null generated always as identity,"
					+ "carte varchar(12),"
					+ "HE_1 varchar(20),"
					+ "HE_2 varchar(20),"
					+ "HS_1 varchar(20),"
					+ "HS_2 varchar(20),"
					+" PRIMARY KEY (id))";
			break;

		}//Fin Switch

		try {

			metas = Database.connection.getMetaData();
			stat = Database.connection.createStatement();
			tables = metas.getTables(Database.connection.getCatalog(), "APP", table, null);

			if (!tables.next()) {
				
				stat.execute(sqlReq);
				System.out.println("table cr�e");

			}

		} catch (SQLException e) {
			if (e.getSQLState().equalsIgnoreCase("X0Y32")) {
			}
		}

	}// Fin cr�erTable

	public boolean IPVerified() {
		
		//Verifier aupr�s de la base de donn�e contenant la table qui stocke les adresse IP d�j� utilis�s.
		return true;

	}
}
