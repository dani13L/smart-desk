package tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


import database.model.*;
import interfaces.Template;

public class EveController {

	private String carte = "";
	private String service = "";
	public static String eve = "";

	// ity le boolean
	public static boolean hasNewHistoricElement = false;


	private TimeSpans timeSpans = new TimeSpans();
	
	
	//Tables 
	private HeureContinue continues = Template.db_tables.getHeureContinue();
	private Discontinue_double dis_double = Template.db_tables.getDiscontinue_double();
	private Personnel personnel = Template.db_tables.getTablePersonnel();
	private Niditra niditra = Template.db_tables.getNiditra();
	private Nivoaka nivoaka = Template.db_tables.getNivoaka();
	private Historique his_toric = Template.db_tables.getHistorique();

	private LocalTime ankehitriny, truncAnk;

	private boolean discontinue = false;
	private boolean firstIn, SecondIn, firstOut, SecondOut;

	// ... 
	public EveController() {
		super();
	}


	public String checkHours() {

		if (!(this.carte.equalsIgnoreCase(""))) {

			/* Capturer l'heure actuel au moment o� la carte passe */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE);
			ankehitriny = LocalTime.now();
			ankehitriny.format(formatter);
			truncAnk = ankehitriny.truncatedTo(ChronoUnit.SECONDS);

			if (continues.verifier(this.carte)) {

				heureContinue();

			} else if (dis_double.verifier(this.carte)) {

				heureDiscontinue();

			} else {
				
				
				
				/* Heure vari�e */
			}

			// R�initialiser la carte
			this.carte = "";

			return this.service;
		}

		return "Pas de carte";

	}

	private void insertIntoNiditra() {

		switch (personnel.retrieveState(this.carte)) {
		case 0:
			System.out.println("Etat = 0 pour cette personne en heure continue");

			personnel.selectPersonWithCard(this.carte);
			niditra.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
					truncAnk.toString(), LocalDate.now().toString());
			// Set historique eve to manodidina lera fidirana MLF
			eve = "En début de service";
			// Set etat to one
			personnel.updateState(this.carte, 1);

			addToHistoric();

			break;
		case 1:
			System.out.println("Etat = 1 pour cette personne en heure continue");

			/*
			 * L'�tat de pointage 1 veut dire que la personne n'a jamais fait une seconde
			 * pointage en dehors de cette intervalle de temps Mais il faut v�rifier que la
			 * derni�re pointage ne s'est pas pass�e le m�me jour dans cette intervalle
			 * 
			 */
			String lastDate = niditra.selectLastPointage(this.carte, "date");

			if (!(lastDate.equalsIgnoreCase("")) && timeSpans.thisDateIsNow(lastDate) == true) {

				System.out.println("Vous avez d�j� fait une pointage autour de l'heure d'entr�e");

			} else {

				personnel.selectPersonWithCard(this.carte);
				niditra.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
						truncAnk.toString(), LocalDate.now().toString());
				// Set MLF (Manodidina lera fidirana
				eve = "En d�but de service";

				addToHistoric();
			}

			break;

		default:
			break;
		}
		/* Permetre au tableau des d�but de service de se mettre � jour */
		this.service = "niditra";

	}

	private void insertIntoNivoaka() {

		switch (personnel.retrieveState(this.carte)) {
		case 1:

			personnel.selectPersonWithCard(this.carte);
			nivoaka.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
					truncAnk.toString(), LocalDate.now().toString());
			// Set historique eve to manodidina lera fidirana MLF
			eve = "En fin de service";
			// Set etat to one
			personnel.updateState(this.carte, 0);
			addToHistoric();

			break;
		case 0:

			/*
			 * L'�tat de pointage 1 veut dire que la personne n'a jamais fait une seconde
			 * pointage en dehors de cette intervalle de temps Mais il faut v�rifier que la
			 * derni�re pointage ne s'est pas pass�e le m�me jour dans cette intervalle
			 * 
			 */

			String lastDate = nivoaka.selectLastPointage(this.carte, "date");

			if (!(lastDate.equalsIgnoreCase("")) && timeSpans.thisDateIsNow(lastDate) == true) {
				System.out.println("Vous avez d�j� fait une pointage autour de l'heure de sortie");
			} else {
				personnel.selectPersonWithCard(this.carte);
				nivoaka.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
						truncAnk.toString(), LocalDate.now().toString());
				// Set MLF (Manodidina lera fidirana
				eve = "En fin de service";
				addToHistoric();

			}

			break;

		default:
			break;
		}

		/* Permettre au tableau de fin de service de se mettre � jour */
		this.service = "nivoaka";

		//
	}

	private void heureContinue() {
		System.out.println("--------------On a une carte avec heure continue -------------");

		/* Calculer le temps 1 heure avant et apr�s l'heure d'entr�e */
		String gap1 = timeSpans.minusTime(continues.getHE(), 1);
		String gap2 = timeSpans.plusTime(continues.getHE(), 1);

		String gap3 = timeSpans.minusTime(continues.getHS(), 1);
		String gap4 = timeSpans.plusTime(continues.getHS(), 1);

		/*
		 * V�rifier si l'heure du pointage est dans l'intervalle de temps de l'heure de
		 * debut de service
		 */
		if (timeSpans.inBetween(gap1, gap2, ankehitriny)
				|| timeSpans.BeforeTime(ankehitriny, continues.getHE())) {

			insertIntoNiditra();


		} else if (timeSpans.inBetween(gap3, gap4, ankehitriny)) {
			/* V�rifier si c'est dans l'intervalle du fin de service */
			insertIntoNivoaka();

		} else if (timeSpans.inBetween(gap1, gap2, ankehitriny)
				&& timeSpans.inBetween(gap3, gap4, ankehitriny)) {
			eve = "E.D.E.F";
		} else {
			eve = "En milieu de journée";
		}
	}
	
	private void heureDiscontinue() {
		System.out.println("--------------On a une carte avec heure DISCONTINUE -------------");

		String gap1 = timeSpans.minusTime(dis_double.getHE_1(), 1);
		String gap2 = timeSpans.plusTime(dis_double.getHE_1(), 1);
		String gap3 = timeSpans.minusTime(dis_double.getHS_1(), 1);
		String gap4 = timeSpans.plusTime(dis_double.getHS_1(), 1);
		String gap5 = timeSpans.minusTime(dis_double.getHE_2(), 1);
		String gap6 = timeSpans.plusTime(dis_double.getHE_2(), 1);
		String gap7 = timeSpans.minusTime(dis_double.getHS_2(), 1);
		String gap8 = timeSpans.plusTime(dis_double.getHS_2(), 1);

		if (timeSpans.inBetween(gap1, gap2, ankehitriny) || timeSpans.inBetween(gap5, gap6, ankehitriny)) {
			// Un petit condition pour verifier un double check
			if (timeSpans.inBetween(gap1, gap2, ankehitriny)) {
				firstIn = true;
				SecondIn = false;
			} else if (timeSpans.inBetween(gap5, gap6, ankehitriny)) {
				SecondIn = true;
				firstIn = false;
			}

			switch (personnel.retrieveState(this.carte)) {
			case 0:
				System.out.println("Etat = 0 discontinue");
				personnel.selectPersonWithCard(this.carte);
				niditra.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
						truncAnk.toString(), LocalDate.now().toString());
				// Set historique eve to manodidina lera fidirana MLF
				eve = "En d�but de service";
				// Set etat to one
				personnel.updateState(this.carte, 1);

				addToHistoric();

				break;
			case 1:
				System.out.println("Etat = 1 discontinue");
				String lastDate = niditra.selectLastPointage(this.carte, "date");
				if (!lastDate.equalsIgnoreCase("") && timeSpans.thisDateIsNow(lastDate) == true) {

					LocalTime lastTime = LocalTime.parse(niditra.selectLastPointage(this.carte, "time"));
					System.out.println("Heure du deriner pointage de cette personne: " + lastTime.toString());

					if (firstIn) {
						if (timeSpans.inBetween(gap1, gap2, lastTime)) {
					
							System.out.println(
									"Vous (dis_double) avez d�j� fait une pointage ! Message du premi�re Heure");
						}
					} else if (SecondIn) {
						if (timeSpans.inBetween(gap5, gap6, lastTime)) {
						
							System.out.println(
									"Vous (dis_double) avez d�j� fait une pointage !Message du second Heure");
						}
					}else {
						System.out.println("On ajoute quand m�me, car second heure");
						personnel.selectPersonWithCard(this.carte);
						niditra.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
								truncAnk.toString(), LocalDate.now().toString());
						// Set MLF (Manodidina lera fidirana
						eve = "En début de service";

						addToHistoric();
				
					}

				} else {
					System.out.println("On ajoute quand m�me");
					personnel.selectPersonWithCard(this.carte);
					niditra.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(),this.carte,
							truncAnk.toString(), LocalDate.now().toString());
					// Set MLF (Manodidina lera fidirana
					eve = "En d�but de service";

					addToHistoric();
				}

				break;

			default:
				break;
			}// FIN SWITCH CASE

			this.service = "niditra";
			//

		} else if (timeSpans.inBetween(gap3, gap4, ankehitriny)
				|| timeSpans.inBetween(gap7, gap8, ankehitriny)) {
			if (timeSpans.inBetween(gap3, gap4, ankehitriny)) {
				firstOut = true;
				SecondOut = false;
			} else if (timeSpans.inBetween(gap7, gap8, ankehitriny)) {
				SecondOut = true;
				firstOut = false;
			}

			switch (personnel.retrieveState(this.carte)) {

			case 1:
				System.out.println("Etat = 1 discontinue");
				personnel.selectPersonWithCard(this.carte);
				nivoaka.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
						truncAnk.toString(), LocalDate.now().toString());
				// Set historique eve to manodidina lera fidirana MLF
				eve = "En fin de service";
				// Set etat to one
				personnel.updateState(this.carte, 0);

				addToHistoric();
				//
				break;
			case 0:

				String lastDate = nivoaka.selectLastPointage(this.carte, "date");
				if (!lastDate.equalsIgnoreCase("") && timeSpans.thisDateIsNow(lastDate) == true) {

					LocalTime lastTime = LocalTime.parse(nivoaka.selectLastPointage(this.carte, "time"));
					System.out.println(
							"Heure du deriner pointage de sortie de cette personne: " + lastTime.toString());

					if (firstOut) {
						if (timeSpans.inBetween(gap3, gap4, lastTime)) {
							System.out.println(
									"Vous (dis_double) avez d�j� fait une pointage de SORTIE ! Message du premi�re Heure");
						}
					} else if (SecondOut) {
						if (timeSpans.inBetween(gap7, gap8, lastTime)) {
							System.out.println(
									"Vous (dis_double) avez d�j� fait une pointage de SORTIE !Message du second Heure");
						}
					}else {
						
						System.out.println("On ajoute quand m�me En SORTIE");
						personnel.selectPersonWithCard(this.carte);
						nivoaka.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
								truncAnk.toString(), LocalDate.now().toString());
						// Set MLF (Manodidina lera fidirana
						eve = "En fin de service";

						addToHistoric();
					}
					

				} else {
					System.out.println("On ajoute quand m�me En SORTIE");
					personnel.selectPersonWithCard(this.carte);
					//...
					nivoaka.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), this.carte,
							truncAnk.toString(), LocalDate.now().toString());
					// Set MLF (Manodidina lera fidirana
					eve = "En fin de service";

					// add to historic
					addToHistoric();
				}
				break;
				//...

			default:
				break;
			}

			this.service = "nivoaka";

			// 
			
			/*Si la dur�e du temps de travail est de seulement d'une heure*/
		} else if ((timeSpans.inBetween(gap1, gap2, ankehitriny)
				&& timeSpans.inBetween(gap3, gap4, ankehitriny))
				|| (timeSpans.inBetween(gap5, gap6, ankehitriny)
						&& timeSpans.inBetween(gap7, gap8, ankehitriny)
						|| (timeSpans.inBetween(gap3, gap4, ankehitriny)&&timeSpans.inBetween(gap5, gap6, ankehitriny)))) {
			
			eve = "E.D.E.F";
			
		/*Si le pointage se passe entre les heures de pointes */
		} else {
			eve = "En milieu de journée";
		}
	}

	public void addToHistoric(){
		hasNewHistoricElement= true;
		ResultSet set = Template.db_tables.getTablePersonnel().selectPhoto(carte);

		try {
			if(set.next()){
				String photo = set.getString("Photo");
				his_toric.insert(personnel.getMpiasa().getNom(), personnel.getMpiasa().getPrenom(), eve, truncAnk.toString(), this.carte, photo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


	// Accesseurs 
	public String getEve() {
		return eve;
	}

	public void setEve(String eve) {
		this.eve = eve;
	}

	public String getCarte() {
		return carte;
	}

	public void setCarte(String carte) {
		this.carte = carte;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}


	
}
