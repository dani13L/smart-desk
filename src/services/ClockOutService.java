package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

import interfaces.ClockOut;
import interfaces.Template;
import tasks.WebSocket;
import utilities.checkings.WorkerCheck;

public class ClockOutService {

    private ClockOut clock_out = new ClockOut();
    DefaultListModel<WorkerCheck> model = (DefaultListModel<WorkerCheck>) clock_out.getList().getModel();


    public ClockOutService(){

        load();
        refresh();
    }

    private void load(){
        
        SwingWorker work = new SwingWorker<Void, ArrayList<String[]>>() {

            @Override
            protected Void doInBackground() throws Exception {
                ResultSet set,res;
                try {
                    set = Template.db_tables.getNivoaka().select();
                    String[] elts;
                    ArrayList<String[]> list = new ArrayList<>();
                    while (set.next()) {

                        String carte = set.getString("Carte");

                        // Récupérer la photo de la personne 
                        res = Template.db_tables.getTablePersonnel().selectPhoto(carte);
                        String photo = "";
                        if(res.next()){
                            photo = res.getString("Photo");

                            String Heure_de_sortie = "";

                            // Récupérer heure de sortie
                            if(Template.db_tables.getHeureContinue().verifier(carte)){

                                Heure_de_sortie = Template.db_tables.getHeureContinue().getHS();

                            }else if(Template.db_tables.getDiscontinue_double().verifier(carte)){

                                Heure_de_sortie = Template.db_tables.getDiscontinue_double().getHS_2();

                            }

                            elts = new String[] {
                                photo,
                                set.getString("Nom")+" "+set.getString("Prenom"),
                                Heure_de_sortie,
                                set.getString("Depart"),
                                String.valueOf(set.getInt("id"))
                            };
                            list.add(elts);
                        }
                        
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

                    model.addElement(new WorkerCheck(elt[0],elt[1],elt[2],elt[3]));
                    clock_out.revalidate();
                    clock_out.repaint();

                }
            }
        };

        work.execute();
    }
    
    // pour actualiser la liste
    private void refresh(){

        SwingWorker worker = new SwingWorker<Void, String[]>() {

            @Override
            protected Void doInBackground() throws Exception {

                // ? Pendant que la fenêtre d'ajout détecte une carte
                String[] lastPersonInfo;

                while (true) {

                    // Pause
                    Thread.sleep(200);

                    // ? Récupérer depuis la base
                    if (! WebSocket.badge.equalsIgnoreCase("") ) {

                        Template.time_control.setCarte(WebSocket.badge);

                        if(Template.time_control.checkHours().equalsIgnoreCase("nivoaka")){
                            ResultSet set = Template.db_tables.getNivoaka().selectLastElement();
                            
                            if(set.next()){

                                String carte = set.getString("Carte");
                                // Récupérer la photo de la personne 
                                ResultSet res = Template.db_tables.getTablePersonnel().selectPhoto(carte);
                                String photo = "";

                                if(res.next()){

                                    photo = res.getString("Photo");

                                    String Heure_de_sortie = "";

                                    // Récupérer heure de sortie
                                    if(Template.db_tables.getHeureContinue().verifier(carte)){

                                        Heure_de_sortie = Template.db_tables.getHeureContinue().getHS();

                                    }else if(Template.db_tables.getDiscontinue_double().verifier(carte)){

                                        Heure_de_sortie = Template.db_tables.getDiscontinue_double().getHS_2();

                                    }

                                    lastPersonInfo = new String[]{
                                        photo,
                                        set.getString("Nom")+" "+set.getString("Prenom"),
                                        Heure_de_sortie,
                                        set.getString("Depart")
                                    };

                                    publish(lastPersonInfo);


                                }

                            }
                        }

                        WebSocket.badge = "";
                    }

                }
            }

            @Override
            protected void process(List<String[]> chunks) {

                // ? Récupérer le dernier élement du tableau
                String[] retrieve = chunks.get(0);
                model.addElement(new WorkerCheck(retrieve[0], retrieve[1], retrieve[2], retrieve[3]));

            }
        };

        worker.execute();
    }

    public ClockOut getClock_out() {
        return clock_out;
    }

    public void setClock_out(ClockOut clock_out) {
        this.clock_out = clock_out;
    }
    
    
}
