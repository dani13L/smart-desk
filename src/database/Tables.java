package database;

import java.sql.SQLException;

import database.model.Discontinue_double;
import database.model.HeureContinue;
import database.model.Historique;
import database.model.Niditra;
import database.model.Nivoaka;
import database.model.Personnel;
import database.model.Utilisateur;

public class Tables {
	
	/* 
	 * CLASSE POUR INSTANCIER TOUT LES TABLES
	 * */
	
	private Personnel tablePersonnel;
	private Historique historique;
	private Niditra niditra;
	private Nivoaka nivoaka;
	private Utilisateur utilisateur;
	private HeureContinue heureContinue;
	private Discontinue_double discontinue_double;
	
	public Tables() {
		try {
			
			tablePersonnel = new Personnel();
			historique = new Historique();
			niditra = new Niditra();
			heureContinue = new HeureContinue();
			discontinue_double = new Discontinue_double();
			nivoaka = new Nivoaka();
			utilisateur=new Utilisateur();
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	public Nivoaka getNivoaka() {
		return nivoaka;
	}



	public void setNivoaka(Nivoaka nivoaka) {
		this.nivoaka = nivoaka;
	}



	public Niditra getNiditra() {
		return niditra;
	}
	public void setNiditra(Niditra niditra) {
		this.niditra = niditra;
	}
	public Historique getHistorique() {
		return historique;
	}
	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
	public Personnel getTablePersonnel() {
		return tablePersonnel;
	}
	public void setTablePersonnel(Personnel crp) {
		this.tablePersonnel = crp;
	}



	public HeureContinue getHeureContinue() {
		return heureContinue;
	}



	public void setHeureContinue(HeureContinue heureContinue) {
		this.heureContinue = heureContinue;
	}



	public Utilisateur getUtilisateur() {
		return utilisateur;
	}



	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}



	public Discontinue_double getDiscontinue_double() {
		return discontinue_double;
	}



	public void setDiscontinue_double(Discontinue_double discontinue_double) {
		this.discontinue_double = discontinue_double;
	}
	
	
	
}
