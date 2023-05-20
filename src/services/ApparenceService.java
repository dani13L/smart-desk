package services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import interfaces.Apparence;
import interfaces.ClockIn;
import interfaces.ClockOut;
import interfaces.Template;
import utilities.Labels;

public class ApparenceService {
    private ResourceBundle messages;
    private Apparence apparence=new Apparence();
    private Locale currentLocale = Locale.FRANCE;

     private DefaultComboBoxModel modele;

     
    
    public ApparenceService(){
        // -------------------------changement de langue------------------------------------
       apparence.getBox().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = apparence.getBox().getSelectedIndex();
                if(selectedIndex == 0){
                    currentLocale = Locale.FRANCE;
                }else{
                    currentLocale = new Locale("mg","MG");
                }
                messages = ResourceBundle.getBundle("resources/messages", currentLocale);
               String[] valeur = { messages.getString("sur_nom"), messages.getString("couleur_pref"),
                       messages.getString("vil_natal") };
                modele = new DefaultComboBoxModel<String>(valeur);

                updateInterfaceLangue();
                
            }

        });

        // -------------------------changement de taille------------------------------------
        apparence.getBoxtaille().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = apparence.getBoxtaille().getSelectedIndex();
                if(selectedIndex==0){
                    updateInterfacePolice(15);
                }
                else if(selectedIndex==1){
                    updateInterfacePolice(50);
                }
                else{
                    updateInterfacePolice(75);
                }
                
            }

        });
    }

    // -------------------------------------------------------------------Methode update langue---------------------------------------------------------------------------------------------------------------------------------
    private void updateInterfaceLangue(){
        apparence.titre.setText(messages.getString("titre"));
        apparence.language.setText(messages.getString("lang"));
        apparence.taille.setText(messages.getString("taille"));
        apparence.theme.setText(messages.getString("them"));

        Template.menuServices.getMenu().dash.getMenu().setText(messages.getString("tab"));
        Template.menuServices.getMenu().debut.getMenu().setText(messages.getString("deb"));
        Template.menuServices.getMenu().fin.getMenu().setText(messages.getString("fin"));
        Template.menuServices.getMenu().list.getMenu().setText(messages.getString("pers"));
        Template.menuServices.getMenu().setting.getMenu().setText(messages.getString("param"));
        
        Template.menuServices.getDashboard().profile.getNom().setText(messages.getString("user"));
        Template.menuServices.getDashboard().profile.getPost().setText(messages.getString("post"));
        Template.menuServices.getDashboard().getWelcomeLabel().setText(messages.getString("tonga"));

        Template.menuServices.getDashboard().getParametreService().getParametre().menuApparence.getMenu().setText(messages.getString("titre"));
        Template.menuServices.getDashboard().getParametreService().getParametre().menuScan.getMenu().setText(messages.getString("scan_bag"));
        Template.menuServices.getDashboard().getParametreService().getParametre().menuCompte.getMenu().setText(messages.getString("compte"));
        Template.menuServices.getDashboard().getParametreService().getParametre().menuExporter.getMenu().setText(messages.getString("export"));
        Template.menuServices.getDashboard().getParametreService().getParametre().menuApropo.getMenu().setText(messages.getString("propos"));
        Template.menuServices.getDashboard().getParametreService().getParametre().menuTerm_cond.getMenu().setText(messages.getString("term"));

        // pour le welcome label
        Template.menuServices.getDashboard().getWelcomeLabel().setText(messages.getString("param"));

        //Account
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getUsernameLabel().setText(messages.getString("username"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getChangePass().setText(messages.getString("changePass"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getChangeSQ().setText(messages.getString("changeQs"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getChangeCompte().setText(messages.getString("changeCompte"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getProfile().getNom().setText(messages.getString("user"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getProfile().getPost().setText(messages.getString("post"));

        //historique
        Template.menuServices.getDashboard().getHistoricService().getHistorics().getTitle().setText(messages.getString("histo"));
        Template.menuServices.getDashboard().getHistoricService().getHistorics().getDeviceLabel().setText(messages.getString("dispo"));
        //scanner Rfid
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getPort().setText(messages.getString("choisiPort"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getPortSuite().setText(messages.getString("moduleList"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getTitre().setText(messages.getString("scan_rfid"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getSsid().setText(messages.getString("scan_rfid"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getPass().setText(messages.getString("mdp"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getBouton().getTextLabel().setText(messages.getString("btn_suivant"));


         //taux de présence/ ponctualité
         ClockIn.overview.getTitle().setText(messages.getString("resume_hier"));
         ClockIn.overview.getPresPourcent().getNam().setText(messages.getString("taux_pres"));
         ClockIn.overview.getPonctPourcent().getNam().setText(messages.getString("taux_ponct"));
         ClockOut.overview.getTitle().setText(messages.getString("resume_hier"));
         ClockOut.overview.getPresPourcent().getNam().setText(messages.getString("taux_pres"));
         ClockOut.overview.getPonctPourcent().getNam().setText(messages.getString("taux_ponct"));

         //prévu
         Template.menuServices.getDashboard().getClockInService().getClock_in().getEmma().getSchedule().setText(messages.getString("prevu")+"7h00-18h00");
         Template.menuServices.getDashboard().getClockInService().getClock_in().getCecil().getSchedule().setText(messages.getString("prevu")+"7h00-18h00");

        //  Log in
         Template.logInService.getLogin().getLabUserName().setText(messages.getString("username"));
         Template.logInService.getLogin().getLabPass().setText(messages.getString("mdp"));
         Template.logInService.getLogin().getLabCreer().setText(messages.getString("create_compte"));
         Template.logInService.getLogin().getLabForgotPass().setText(messages.getString("forget_pass"));
         Template.logInService.getLogin().getLabPhrase().setText(messages.getString("pas_compte"));
         Template.logInService.getLogin().getBtnConnect().getTextLabel().setText(messages.getString("btn_conect"));

         //creation compte
         Template.logInService.getLogin().getCreationService().getCreation().getNomLabel().setText(messages.getString("nom"));
         Template.logInService.getLogin().getCreationService().getCreation().getPrenomLabel().setText(messages.getString("prenom"));
         Template.logInService.getLogin().getCreationService().getCreation().getUsernameLabel().setText(messages.getString("username"));
         Template.logInService.getLogin().getCreationService().getCreation().getPassLabel().setText(messages.getString("create_mdp"));
         Template.logInService.getLogin().getCreationService().getCreation().getConfirmLabel().setText(messages.getString("confirm_mdp"));
         Template.logInService.getLogin().getCreationService().getCreation().getFanazavana().setText(messages.getString("fanazavana"));
         Template.logInService.getLogin().getCreationService().getCreation().getTitre().setText(messages.getString("create_compte"));
         Template.logInService.getLogin().getCreationService().getCreation().getNext().getTextLabel().setText(messages.getString("btn_suivant"));
         Template.logInService.getLogin().getCreationService().getCreation().getRetour().setText(messages.getString("btn_retour"));

        Template.logInService.getLogin().getCreationService().getCreation().getQuestionsBox().setModel(modele);
         
        //mot de passe oublié
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getLabTitre().setText(messages.getString("forget_pass"));
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getLabNom().setText(messages.getString("nom"));
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getLabPrenom().setText(messages.getString("prenom"));
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getLabFanazavana().setText(messages.getString("check_reponse"));
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getFanazavana().setText(messages.getString("check_nom_prenom"));
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getNext().getTextLabel().setText(messages.getString("btn_suivant"));
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getRetour().setText(messages.getString("btn_retour"));

        //Exporter
        Template.menuServices.getDashboard().getParametreService().getParametre().getExporterService().getExporter().getTitre().setText(messages.getString("export"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getExporterService().getExporter().getExporterBtn().getTextLabel().setText(messages.getString("export"));
        Template.menuServices.getDashboard().getParametreService().getParametre().getExporterService().getExporter().getLanguage().setText(messages.getString("export_person"));
        //Apropos
        Template.menuServices.getDashboard().getParametreService().getParametre().getAproposService().getApropos().getTitre().setText(messages.getString("propos"));

        // terme et condition
        Template.menuServices.getDashboard().getParametreService().getParametre().getTerm_conditionService().getTerm_Condition().getTitre().setText(messages.getString("term"));

        //personne
        Template.menuServices.getDashboard().getPersonneService().getPersonnels().getPosta().getLkey().setText(messages.getString("post"));
        Template.menuServices.getDashboard().getPersonneService().getPersonnels().getCarte().getLkey().setText(messages.getString("carte"));

        //ajouter
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getInfoPerso().setBorder(BorderFactory.createTitledBorder(messages.getString("info_person")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getNom().setText(messages.getString("nom"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getPrenom().setText(messages.getString("prenom"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getCin().setText(messages.getString("cin"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getPhoto().setText(messages.getString("photo"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getInfoPoste().setBorder(BorderFactory.createTitledBorder(messages.getString("info_post")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getTitre().setText(messages.getString("title_poste"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getpHeure().setBorder(BorderFactory.createTitledBorder(messages.getString("work_heur")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getSemaine().setText(messages.getString("par_sem"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getEntree().setText(messages.getString("debut"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getSortie().setText(messages.getString("fin"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getpService().setBorder(BorderFactory.createTitledBorder(messages.getString("deb_fin")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getAleatoire().setText(messages.getString("varie"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getFixed().setText(messages.getString("fixe"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().gethContinue().setText(messages.getString("h_cont"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().gethDiscontinue().setText(messages.getString("h_discont"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getHintCarte().setText(messages.getString("hint_carte"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getPassezCarte().setText(messages.getString("passez_carte"));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getpCarte().setBorder(BorderFactory.createTitledBorder(messages.getString("p_carte")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getInfoId().setBorder(BorderFactory.createTitledBorder(messages.getString("info_id")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getPanelContinuite().setBorder(BorderFactory.createTitledBorder(messages.getString("p_continute")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getOk().getTextLabel().setText(messages.getString("btn_ajout"));
        


        
    }

    //-----------------------------------------------------------Methode update police------------------------------------------------------------------------------------------------------------------------------------------
    public void updateInterfacePolice(int pourcentage){
        apparence.titre.changeFont(pourcentage);
        apparence.language.changeFont(pourcentage);
        apparence.taille.changeFont(pourcentage);
        apparence.theme.changeFont(pourcentage);

        Template.menuServices.getMenu().dash.changeFont(pourcentage);
        Template.menuServices.getMenu().debut.changeFont(pourcentage);
        Template.menuServices.getMenu().fin.changeFont(pourcentage);
        Template.menuServices.getMenu().list.changeFont(pourcentage);
        Template.menuServices.getMenu().setting.changeFont(pourcentage);

        Template.menuServices.getDashboard().profile.changeFont(pourcentage);

        Template.menuServices.getDashboard().getParametreService().getParametre().menuApparence.changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().menuScan.changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().menuCompte.changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().menuExporter.changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().menuApropo.changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().menuTerm_cond.changeFont(pourcentage);

        ((Labels) Template.menuServices.getDashboard().getWelcomeLabel()).changeFont(pourcentage);

        //Account
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getUsernameLabel().changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getChangePass().changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getChangeSQ().changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getChangeCompte().changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getProfile().changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().getAccountService().getAccount().getProfile().changeFont(pourcentage);

        //historique
        Template.menuServices.getDashboard().getHistoricService().getHistorics().getTitle().changeFont(pourcentage);
        Template.menuServices.getDashboard().getHistoricService().getHistorics().getDeviceLabel().changeFont(pourcentage);

        //scanner Rfid
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getPort().changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getPortSuite().changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().getScannerRFIDService().getScanner().getTitre().changeFont(pourcentage);

        //taux de présence/ ponctualité
         ClockIn.overview.getTitle().changeFont(pourcentage);
         ClockIn.overview.getPresPourcent().getNam().changeFont(pourcentage);
         ClockIn.overview.getPonctPourcent().getNam().changeFont(pourcentage);
         ClockOut.overview.getTitle().changeFont(pourcentage);
         ClockOut.overview.getPresPourcent().getNam().changeFont(pourcentage);
         ClockOut.overview.getPonctPourcent().getNam().changeFont(pourcentage);

         //prévu
         Template.menuServices.getDashboard().getClockInService().getClock_in().getEmma().getSchedule().changeFont(pourcentage);
         Template.menuServices.getDashboard().getClockInService().getClock_in().getEmma().getNam().changeFont(pourcentage);
         Template.menuServices.getDashboard().getClockInService().getClock_in().getCecil().getSchedule().changeFont(pourcentage);
         Template.menuServices.getDashboard().getClockInService().getClock_in().getCecil().getNam().changeFont(pourcentage);

          //  Log in
          Template.logInService.getLogin().getLabUserName().changeFont(pourcentage);
          Template.logInService.getLogin().getLabPass().changeFont(pourcentage);
          Template.logInService.getLogin().getLabCreer().changeFont(pourcentage);
          Template.logInService.getLogin().getLabForgotPass().changeFont(pourcentage);

          //creation compte
         Template.logInService.getLogin().getCreationService().getCreation().getNomLabel().changeFont(pourcentage);
         Template.logInService.getLogin().getCreationService().getCreation().getPrenomLabel().changeFont(pourcentage);
         Template.logInService.getLogin().getCreationService().getCreation().getUsernameLabel().changeFont(pourcentage);
         Template.logInService.getLogin().getCreationService().getCreation().getPassLabel().changeFont(pourcentage);
         Template.logInService.getLogin().getCreationService().getCreation().getConfirmLabel().changeFont(pourcentage);
         Template.logInService.getLogin().getCreationService().getCreation().getFanazavana().changeFont(pourcentage);
         Template.logInService.getLogin().getCreationService().getCreation().getTitre().changeFont(pourcentage);
        //  Template.logInService.getLogin().getCreationService().getCreation().getNext().getTextLabel().setText(messages.getString("btn_suivant"));
        //  Template.logInService.getLogin().getCreationService().getCreation().getRetour().setText(messages.getString("btn_retour"));
        // Template.logInService.getLogin().getCreationService().getCreation().getQuestionsBox().setModel(modele);
         
        //mot de passe oublié
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getLabTitre().changeFont(pourcentage);
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getLabNom().changeFont(pourcentage);
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getLabPrenom().changeFont(pourcentage);
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getLabFanazavana().changeFont(pourcentage);
        Template.logInService.getLogin().getForgetPassService().getForgetPass().getFanazavana().changeFont(pourcentage);
        // Template.logInService.getLogin().getForgetPassService().getForgetPass().getNext().getTextLabel().setText(messages.getString("btn_suivant"));
        // Template.logInService.getLogin().getForgetPassService().getForgetPass().getRetour().setText(messages.getString("btn_retour"));
 
         //Exporter
         Template.menuServices.getDashboard().getParametreService().getParametre().getExporterService().getExporter().getTitre().changeFont(pourcentage);
        
         // terme et condition
        Template.menuServices.getDashboard().getParametreService().getParametre().getTerm_conditionService().getTerm_Condition().getTitre().changeFont(pourcentage);
        Template.menuServices.getDashboard().getParametreService().getParametre().getTerm_conditionService().getTerm_Condition().getMini_titre().changeFont(pourcentage);

         //personne
         Template.menuServices.getDashboard().getPersonneService().getPersonnels().getCIN().getLkey().changeFont(pourcentage);
         Template.menuServices.getDashboard().getPersonneService().getPersonnels().getPosta().getLkey().changeFont(pourcentage);
         Template.menuServices.getDashboard().getPersonneService().getPersonnels().getCarte().getLkey().changeFont(pourcentage);
         
        //ajouter
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getNom().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getPrenom().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getCin().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getPhoto().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getInfoPoste().setBorder(BorderFactory.createTitledBorder(messages.getString("info_post")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getTitre().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getpHeure().setBorder(BorderFactory.createTitledBorder(messages.getString("work_heur")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getSemaine().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getEntree().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getSortie().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getpService().setBorder(BorderFactory.createTitledBorder(messages.getString("deb_fin")));
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getHintCarte().changeFont(pourcentage);
        Template.menuServices.getDashboard().getPersonneService().getAJService().getAjouter().getPassezCarte().changeFont(pourcentage);
        



    }

    public Apparence getApparence() {
        return apparence;
    }

    public void setApparence(Apparence apparence) {
        this.apparence = apparence;
    }
    
}