package services;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.formdev.flatlaf.ui.FlatLineBorder;

import interfaces.Ajouter;
import interfaces.Template;
import tasks.DataModifiable;
import tasks.Verifiable;
import tasks.WebSocket;
import utilities.Colors;
import utilities.Sary;

public class AjouterService extends JDialog implements Verifiable {

	/**
	 * Classe pour afficher le menu d'ajout dans une
	 * dialogue
	 */
	private static final long serialVersionUID = 1L;

	private Ajouter ajouter;
	public static String carte = "";

	// pour détecter un badge
	private boolean detecting = false;
	private String message = "";
	private boolean existe = false, cas_cin = false, cas_carte = false;
	public static boolean actualiser = false;

	public AjouterService() {

		ajouter = new Ajouter(DataModifiable.frame);

		// Configurer l'UI du dialogue
		setupUI();
		detectBadge();

		// Ajouter dans la base de donnée.
		ajouter.getOk().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);

				/*
				 * il faut d'abord vérifier si tout les champs sont bien remplis
				 * ensuite il faut vérifier si la personne en question existe déjà dans la base
				 * de donnée
				 * Si tout ces conditions sont sans contrainte ou je ne sais quoi, alors on
				 * ajoute la personne dans la base
				 * 
				 */
				if (allFieldFilled()) {

					// ? Vérifier si la personne extiste déjà dans la base de donnée
					if (existe()) {
						// Rien
					} else {
						inserer();
					}
				}

			}
		});

	}

	// Fonction qui vérifie si tout les champs sont bien remplis
	private boolean allFieldFilled() {

		boolean value = true;

		// Parcourir la liste de textfield
		for (JTextField fill : ajouter.getField()) {

			// ! Vérifier si le champ de texte est vide
			if (fill.getText().isEmpty()) {

				fill.setBorder(new FlatLineBorder(new Insets(10, 10, 10, 10), Color.red, 2, 0));
				fill.setText("Veuillez remplir ce champs");
				fill.setForeground(Color.red);

				value = false;
				// break;
			}

		}
		// ! Quand aucune carte n'est encore assigné
		if (AjouterService.carte.equalsIgnoreCase("")) {

			ajouter.getPassezCarte().setText("Veuillez assigner un carte.");
			ajouter.setForeground(Color.red);
			value = false;

		}

		return value;
	}

	// Fonction qui ajoute dans la base de donnée
	private void inserer() {

		// Liste des champs
		ArrayList<String> champs = new ArrayList<>();
		String image = ajouter.getImage();

		// Boucler les champs de texte
		for (JTextField field : ajouter.getField()) {
			champs.add(field.getText());
		}

		// ? Ajouter dans la table des peronnels
		Template.db_tables.getTablePersonnel().ajouter(champs.get(0), champs.get(1), champs.get(3),
				Long.parseLong(champs.get(2)), Integer.parseInt(champs.get(4)), AjouterService.carte, image, 0);

		if (ajouter.getAleatoire().isSelected()) {
			// ? Heure aléatoire
		} else if (ajouter.getFixed().isSelected()) {

			if (ajouter.gethContinue().isSelected()) {

				String fidira = ajouter.getDebut1().getHours().getHourPicker().getText(),
						firava = ajouter.getFin1().getHours().getHourPicker().getText();

				// ? Enregistrer les heures
				Template.db_tables.getHeureContinue().ajouter(AjouterService.carte, fidira, firava);

			} else if (ajouter.gethDiscontinue().isSelected()) {

				String fidira1 = ajouter.getDebut1().getHours().getHourPicker().getText(),
						firava1 = ajouter.getFin1().getHours().getHourPicker().getText(),
						fidira2 = ajouter.getDebut2().getHours().getHourPicker().getText(),
						firava2 = ajouter.getFin2().getHours().getHourPicker().getText();

				// ? Enregistrer les heures
				Template.db_tables.getDiscontinue_double().ajouter(AjouterService.carte, fidira1, fidira2, firava1, firava2);

			}

		}
		AjouterService.actualiser = true;

	}

	// ? fonction pour détecter un badge
	private void detectBadge() {

		// ? initialiser tout les paramètres en jeu
		detecting = true;
		if (!WebSocket.badge.equalsIgnoreCase(""))
			WebSocket.badge = "";

		SwingWorker detectionWorker = new SwingWorker<Void, String>() {

			// * Créer une image
			Image image = null;

			@Override
			protected Void doInBackground() throws Exception {

				// Boucle pour détecter une carte
				while (detecting) {
					Thread.sleep(100);

					// * Si le badge n'est pas vide
					if (!(WebSocket.badge.equalsIgnoreCase(""))) {

						// ? Vérifier si le badge est déjà assigné
						if ((!AjouterService.carte.equalsIgnoreCase("")) && AjouterService.carte.equalsIgnoreCase(WebSocket.badge)) {

							publish("Vous avez assigné la même carte.");
							WebSocket.badge = "";

						} else {

							AjouterService.carte = WebSocket.badge;
							image = new Sary().Resize("img/reussit.png", 17, 15);
							publish("Une nouvelle carte est assignée.");
							WebSocket.badge = "";

						} /*
							 * if (existe()) {
							 * // ? Carte déjà utilisée
							 * if(cas_carte){
							 * image = new Sary().Resize("img/alert.png", 17, 15);
							 * publish("Cette carte est déjà utilisée.");
							 * }
							 * 
							 * }
							 */

					}
					// Vérification CIN
					/*
					 * if(existe()){
					 * // ? CIN déjà utilisée
					 * if(cas_cin){
					 * publish("Cette pièce d'identitée est déjà utilisée");
					 * }
					 * }
					 */

				}
				return null;
			}

			// Process
			@Override
			protected void process(List<String> chunks) {
				// ? Changer l'information à afficher

				ajouter.getPassezCarte().setText(chunks.get(chunks.size() - 1));
				ajouter.setForeground(Colors.text);

			}

			@Override
			protected void done() {
				System.out.println("Fini d'ajouter");
			}
		};

		detectionWorker.execute();

	}

	// Interface d'ajout
	private void setupUI() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550, 600);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		add(ajouter, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {

				// ? Annuler la détection de badge
				detecting = false;
			}
		});
	}

	// Getter and setter
	public Ajouter getAjouter() {
		return ajouter;
	}

	public void setAjouter(Ajouter ajouter) {
		this.ajouter = ajouter;
	}

	@Override
	public boolean existe() {
		existe = false;
		try {

			// * Query
			ResultSet result = Template.db_tables.getTablePersonnel().select();
			String cin = ajouter.getTcin().getText();
			// ? Récupérer les données de la base
			while (result.next()) {

				// ! Quand la personne existe déjà dans la base

				if (!cin.equalsIgnoreCase("") &&
						(Long.parseLong(cin) == result.getLong("CIN"))) {
					existe = true;
					cas_cin = true;
					System.out.println("CIN existe déjà");

				}
				if (AjouterService.carte.equalsIgnoreCase(result.getString("Carte"))) {

					existe = true;
					cas_carte = true;
					System.out.println("Carte existe déjà");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return existe;
	}

	public boolean isActualiser() {
		return actualiser;
	}

	public void setActualiser(boolean actualiser) {
		this.actualiser = actualiser;
	}

	public boolean isDetecting() {
		return detecting;
	}

	public void setDetecting(boolean detecting) {
		this.detecting = detecting;
	}

}
