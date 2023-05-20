package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

import database.model.Historique;
import interfaces.Historics;
import interfaces.Template;
import tasks.EveController;
import tasks.Verifiable;
import utilities.historic.Historic;

public class HistoricService implements Verifiable{

    private Historics historics = new Historics();
    private String carte = "";
    LocalTime time = LocalTime.now();
    DefaultListModel<Historic> model = (DefaultListModel<Historic>) historics.getList().getModel();

    public HistoricService(){

        // ? Charger la liste
        load();

        // ? Actualiser la liste
        actualiser();

    }
    private void load(){

    

            SwingWorker work = new SwingWorker<Void, ArrayList<String[]>>() {
    
                @Override
                protected Void doInBackground() throws Exception {
                    ResultSet hst;
                    try {
                        hst = Template.db_tables.getHistorique().select();
                        String[] elts;
                        ArrayList<String[]> list = new ArrayList<>();
                        while (hst.next()) {
    
                            String carte = hst.getString("Carte");
                            String heure ="";
    
                                if(Template.db_tables.getHeureContinue().verifier(carte)){
                                    heure= Template.db_tables.getHeureContinue().getHE()+"-"+Template.db_tables.getHeureContinue().getHS();
    
                                }else if (Template.db_tables.getHeureContinue().verifier(carte)){
                                    heure = Template.db_tables.getDiscontinue_double().getHE_1()+"-"+Template.db_tables.getDiscontinue_double().getHS_1()
                                    +"    "+Template.db_tables.getDiscontinue_double().getHE_2()+"-"+Template.db_tables.getDiscontinue_double().getHS_2();
                                }
                            elts = new String[] {
                                    hst.getString("Nom")+" "+
                                    hst.getString("Prenom"),
                                    hst.getString("Eve"),
                                    heure,
                                    hst.getString("Heure"),
                                    hst.getString("Photo")
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
    
                        // mbola mila jerena tsara ito alouha ny zavatra rehetra
                        model.addElement(new Historic(elt[4], elt[0], elt[2], elt[3],elt[1]));
                        historics.revalidate();
                        historics.repaint();
    
                    }

                    historics.getList().setSelectedIndex(0);
                }
            };
    
            work.execute();
    }

    public void actualiser(){
        SwingWorker worker = new SwingWorker<Void, String[]>() {

            @Override
            protected Void doInBackground() throws Exception {

                while(true){

                    Thread.sleep(200);

                    if(EveController.hasNewHistoricElement){

                        ResultSet resultat; 

                        try {
                            String[] elts = null;
                            String heure = "";
                            
    
                            resultat = Template.db_tables.getHistorique().dernierPointage();
                            if(resultat.next()){
                                String carte = resultat.getString("Carte");
    
                                if(Template.db_tables.getHeureContinue().verifier(carte)){
                                    heure= Template.db_tables.getHeureContinue().getHE()+"-"+Template.db_tables.getHeureContinue().getHS();
    
                                }else if (Template.db_tables.getHeureContinue().verifier(carte)){
                                    heure = Template.db_tables.getDiscontinue_double().getHE_1()+"-"+Template.db_tables.getDiscontinue_double().getHS_1()
                                    +"    "+Template.db_tables.getDiscontinue_double().getHE_2()+"-"+Template.db_tables.getDiscontinue_double().getHS_2();
                                }

                                elts = new String []{
                                    resultat.getString("Photo"),
                                    resultat.getString("Nom")+" "+
                                    resultat.getString("Prenom"),
                                    heure,
                                    resultat.getString("Heure"),
                                    resultat.getString("Eve")
    
                                };
    
                            };

                            publish(elts);

                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                            EveController.hasNewHistoricElement = false;
                        }
                
                    }
                
                }
        

                @Override
                protected void process(List<String[]> chunks) {
                    super.process(chunks);
                    String[] lisita = chunks.get(chunks.size()-1);
                    model.addElement((new Historic(lisita[0], lisita[1], lisita[2], lisita[3], lisita[4])));

                }

            } ;
        worker.execute();
    }
    


    public Historics getHistorics() {
        return historics;
    }

    public void setHistorics(Historics historics) {
        this.historics = historics;
    }

    @Override
    public boolean existe() {
        // Carte misy ao @ table personne ? 
        return false;
    }
    
}
