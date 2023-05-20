package services;

import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import interfaces.Personnels;
import interfaces.Template;
import tasks.DataModifiable;
import utilities.peronnel.EditerHeure;
import utilities.peronnel.InfoAdditionnel;
import utilities.peronnel.WorkerCard;
import utilities.peronnel.WorkerInfo;

public class PersonneService {

    private Personnels personnels = new Personnels();
    private boolean actualiser = true;
    DefaultListModel<WorkerCard> model = (DefaultListModel<WorkerCard>) personnels.getList().getModel();
    private String nom, prenom;
    private  AjouterService AJService=new AjouterService();

    public PersonneService() {

        // ? Chargement
        load();

        // ? Afficher l'information concernant la personne selectionnée
        personnels.getList().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    //* Changer la valeur de id pour qu'on puisse modifier l'heure de travail plus tard

                    // ? Vérifier si le mode édition est activée
                    if (personnels.getWorkerInfo().isEditable()) {
                        personnels.getWorkerInfo().setEditable(false);
                        personnels.getEdit().setVisible(false);
                        personnels.setEditable(false);
                    }

                    // ? Afficher la personne séletionné sur le côté

                    if (personnels.getList().getSelectedValue() != null) {

                        DataModifiable.id = personnels.getList().getSelectedValue().getId();

                        nom = personnels.getList().getSelectedValue().getNom();
                        prenom = personnels.getList().getSelectedValue().getPrenom();
                        String photo = personnels.getList().getSelectedValue().getPhoto();

                        // * Charger le nom et prenom de la personne selectionnée

                        personnels.getWorkerInfo().getnoms().setText(nom);
                        personnels.getWorkerInfo().getSchedule().setText(prenom);
                        personnels.getWorkerInfo().changePhoto(photo);

                        // * Charger les informations supplémentaires à propos de la personne
                        charger_information_additionnel(personnels.getList().getSelectedValue());

                    }

                }

            }

        });

        // ? Action du bouton ajouter
        personnels.getAdd().addMouseListener(new MouseInputAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // Afficher une fenêtre d'ajout
                //  AJService = new AjouterService();
                AJService.setVisible(true);
                // ? Actualiser la liste de personnel
                actualiser(AJService);

            }
        });

        // ? Action du bouton supprimer
        personnels.getWorkerInfo().getRemove().addMouseListener(new MouseInputAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // ? Retirer la personne de la base de donnée
                
                String carte = Template.db_tables.getTablePersonnel().select_card_by_id(personnels.getList().getSelectedValue().getId());
                if(Template.db_tables.getHeureContinue().verifier(carte)){
                    Template.db_tables.getHeureContinue().supprimer(carte);
                }else if(Template.db_tables.getDiscontinue_double().verifier(carte)){
                    Template.db_tables.getDiscontinue_double().supprimer(carte);
                }
                Template.db_tables.getTablePersonnel().supprimer(personnels.getList().getSelectedValue().getId());

                model.removeElementAt(personnels.getList().getSelectedIndex());
                personnels.getList().setSelectedIndex(0);

            }

        });

        // ? Action du bouton modifier
        personnels.getWorkerInfo().getEdit().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // ? Afficher les champs de modification
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        personnels.getWorkerInfo().setEditable(true);
                        personnels.getWorkerInfo().importData(nom, prenom);
                        personnels.getEdit().setVisible(true);
                        personnels.setEditable(true);

                    }
                });
                // ? Sauvegarder les modifications
                DataModifiable.modifiable = true;
                memorise_les_donnees(personnels.getWorkerInfo(), personnels.getList().getSelectedValue());

            }
        });

        // ? bouton de validation de modification

        personnels.getEdit().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                DataModifiable.modifiable = false;

                personnels.getWorkerInfo().setEditable(false);
                personnels.getEdit().setVisible(false);
                personnels.setEditable(false);

            }
        });

    }

    // Fonction pour charger les informations de la personne
    private void charger_information_additionnel(WorkerCard travailleur) {

        SwingWorker swingworker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                // ? Faire un query sql
                ResultSet set = Template.db_tables.getTablePersonnel().select(travailleur.getId());

                // ? Variable pour enregistrer la carte de la personne
                String carte = "";

                if (set.next()) {

                    // ? Récupérer les informations nécessaire
                    personnels.setCin(set.getString("CIN"));
                    personnels.setPoste(set.getString("Poste"));
                    carte = set.getString("Carte");

                    // ? Vérifier dans quel tranche d'heure se trouve la personne
                    String heure = "";

                    if (Template.db_tables.getHeureContinue().verifier(carte)) {

                        // ? Juste pour avoir le style 7H-11H
                        String[] debut = Template.db_tables.getHeureContinue().getHE().split(":");
                        String[] fin = Template.db_tables.getHeureContinue().getHS().split(":");
                        heure = debut[0] + "H-" + fin[0] + "H";

                    } else if (Template.db_tables.getDiscontinue_double().verifier(carte)) {

                        // ? Juste pour avoir le style 7H-11H
                        String[] debut = Template.db_tables.getDiscontinue_double().getHE_1().split(":");
                        String[] fin = Template.db_tables.getDiscontinue_double().getHS_2().split(":");
                        heure = debut[0] + "H-" + fin[0] + "H";
                    }
                    personnels.setHeures(heure);

                }

                return null;
            }

            @Override
            protected void done() {

                // * Actualiser les informations additionnels en bas */
                personnels.getInfoList().get(0).getLvalue().setText(personnels.getCin());
                personnels.getInfoList().get(1).getLvalue().setText(personnels.getPoste());
                personnels.getInfoList().get(3).getLvalue().setText(personnels.getHeures());

                // * Pré-remplir les champs de texte
                personnels.getInfoList().get(0).getFvalue().setText(personnels.getCin());
                personnels.getInfoList().get(1).getFvalue().setText(personnels.getPoste());

            }

        };
        swingworker.execute();

    }

    // Fonction qui charge tout les personnels dans la liste
    public void load() {

        SwingWorker work = new SwingWorker<Void, ArrayList<String[]>>() {

            @Override
            protected Void doInBackground() throws Exception {
                ResultSet set;
                try {
                    set = Template.db_tables.getTablePersonnel().select();
                    String[] elts;
                    ArrayList<String[]> list = new ArrayList<>();
                    while (set.next()) {

                        elts = new String[] {
                                set.getString("Photo"),
                                set.getString("Nom"),
                                set.getString("Prenom"),
                                set.getString("Poste"),
                                String.valueOf(set.getInt("id"))
                        };
                        list.add(elts);
                    }
                    publish(list);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void process(List<ArrayList<String[]>> chunks) {
                super.process(chunks);

                // ? Ajouter dans le model
                for (String[] elt : chunks.get(chunks.size() - 1)) {

                    model.addElement(new WorkerCard(elt[0], elt[1], elt[2], elt[3], Integer.parseInt(elt[4])));
                    personnels.revalidate();
                    personnels.repaint();

                }
                personnels.getList().setSelectedIndex(0);
            }
        };

        work.execute();

    }

    // ? Fonction qui actualise la LISTe des personnels
    public void actualiser(AjouterService AJ) {

        SwingWorker worker = new SwingWorker<Void, String[]>() {

            @Override
            protected Void doInBackground() throws Exception {

                // ? Pendant que la fenêtre d'ajout détecte une carte
                while (AJ.isDetecting()) {

                    // Pause
                    Thread.sleep(20);

                    // ? Récupérer depuis la base
                    if (AjouterService.actualiser) {

                        ResultSet set = Template.db_tables.getTablePersonnel().select_for_list(AjouterService.carte);
                        
                        String[] elements;

                        if (set.next()) {

                            // ? Récuperation de donnée
                            elements = new String[] {
                                    set.getString("Photo"),
                                    set.getString("Nom"),
                                    set.getString("Prenom"),
                                    set.getString("Poste"),
                                    String.valueOf(set.getInt("id"))
                            };  
                            publish(elements);
                            
                        }
                        AjouterService.actualiser = false;
                    }

                }
                return null;
            }

            @Override
            protected void process(List<String[]> chunks) {

                // ? Récupérer le dernier élement du tableau
                String[] retrieve = chunks.get(0);
                model.addElement(new WorkerCard(retrieve[0], retrieve[1], retrieve[2], retrieve[3],Integer.parseInt(retrieve[4])));

            }
        };

        worker.execute();
    }

    // * Fonction qui enregistre les informations à modifier pendant la modification
    public void memorise_les_donnees(WorkerInfo info, WorkerCard worker_card) {

        // ? Travail en arrière plan qui récupère tout les données que l'utilisateur à
        // entré
        SwingWorker worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {

                // ? Boucle de récupération
                while (DataModifiable.modifiable) {

                    Thread.sleep(20);

                    // Nouveau data
                    if (DataModifiable.nouveau_data) {

                        DataModifiable.nom = info.getField_nom().getText();
                        DataModifiable.prenom = info.getField_prenom().getText();
                        DataModifiable.ID = Long.parseLong(personnels.getInfoList().get(0).getFvalue().getText());
                        DataModifiable.poste = personnels.getInfoList().get(1).getFvalue().getText();
                        publish();

                        DataModifiable.nouveau_data = false;
                    }
                }

                return null;
            }

            @Override
            protected void process(List<Void> chunks) {
                //? Modifier le nom
                if (!worker_card.getNom()
                        .equalsIgnoreCase(info.getField_nom().getText() + " " + info.getField_prenom().getText())) {
                    worker_card.setNom(DataModifiable.nom);
                    worker_card.getWokerName().setText(DataModifiable.nom + " " + DataModifiable.prenom);

                }

                //? Modifier le prénom
                if (!worker_card.getPrenom().equalsIgnoreCase(info.getField_prenom().getText())) {
                    worker_card.setPrenom(DataModifiable.prenom);
                    worker_card.getWokerName().setText(DataModifiable.nom + " " + DataModifiable.prenom);
                }

                //? Modifier le poste
                if (!worker_card.getPoste().equalsIgnoreCase(personnels.getInfoList().get(1).getFvalue().getText())) {
                    worker_card.setPoste(DataModifiable.poste);
                    worker_card.getTravail().setText(DataModifiable.poste);
                }

                //? Modifier l'image
                if(!DataModifiable.photo.equalsIgnoreCase("") && !DataModifiable.photo.equalsIgnoreCase(worker_card.getPhoto())){
                    
                    info.changePhoto(DataModifiable.photo);
                    worker_card.changerImage(DataModifiable.photo,personnels);
                }

                //? Modifier l'heure
                if(EditerHeure.value_changed){
                    String [] d_debut= DataModifiable.debut.split(":");
                    String [] d_fin= DataModifiable.fin.split(":");
                    String heure = d_debut[0]+"H-"+d_fin[0]+"H";
                    personnels.getInfoList().get(3).getLvalue().setText(heure);

                    EditerHeure.value_changed = false;
                }
                // Actualiser l'interface
                personnels.revalidate();
                personnels.repaint();

            }
        };

        worker.execute();
    }

    // Getters and Setters
    public Personnels getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Personnels personnels) {
        this.personnels = personnels;
    }

    public AjouterService getAJService() {
        return AJService;
    }

    public void setAJService(AjouterService aJService) {
        AJService = aJService;
    }

}
