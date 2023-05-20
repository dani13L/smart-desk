package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

import interfaces.ClockIn;
import interfaces.ClockOut;
import interfaces.Template;
import utilities.checkings.LazyWorker;
import utilities.checkings.Overview;

public class OverviewService {


    DefaultListModel<LazyWorker> model1 = (DefaultListModel<LazyWorker>) ClockIn.overview.getList().getModel();
    DefaultListModel<LazyWorker> model2 = (DefaultListModel<LazyWorker>) ClockOut.overview.getList().getModel();
    
    public OverviewService() {

        load();

    }

    private void load() {

        SwingWorker worker = new SwingWorker<Void, ArrayList<String[]>>() {

            int compte = 0;

            @Override
            protected Void doInBackground() {
                try {
                    // ? Pendant que la fenêtre d'ajout détecte une carte
                    String[] lazyPersonnInfo;
                    ArrayList<String[]> pub = new ArrayList<>();

                    ArrayList<String> personne_niditra = new ArrayList<>();
                    ArrayList<String> personne_nivoaka = new ArrayList<>();
                    ArrayList<String> liste_personne = new ArrayList<>();

                    LocalDate anio = LocalDate.now();
                    LocalDate omaly = anio.minusDays(1);
                    System.out.println(omaly);

                    // Récupérer la liste des personnes niditra
                    ResultSet niditra_set = Template.db_tables.getNiditra().select();
                    String bd_nid = "";
                    String dt_nid = "";
                    while (niditra_set.next()) {

                        bd_nid = niditra_set.getString("Carte");
                        dt_nid = niditra_set.getString("Date");

                        // Récupérer l'information de la date d'hier
                        LocalDate daty = LocalDate.parse(dt_nid);

                        if (daty.equals(omaly)) {
                            personne_niditra.add(bd_nid);
                        }

                    }

                    // Récupérer la liste des personnes nivoaka
                    ResultSet nivoaka_set = Template.db_tables.getNivoaka().select();
                    String bd_vok = "";
                    String dt_vok = "";
                    while (nivoaka_set.next()) {

                        bd_vok = nivoaka_set.getString("Carte");
                        dt_vok = nivoaka_set.getString("Date");

                        LocalDate daty = LocalDate.parse(dt_vok);

                        if (daty.equals(omaly)) {
                            personne_nivoaka.add(bd_vok);
                        }

                    }

                    // Récupérer la liste des personnels
                    ResultSet personnelSet;

                    personnelSet = Template.db_tables.getTablePersonnel().select();

                    String bd_per = "", photo = "", nomenclature = "";
                    while (personnelSet.next()) {

                        bd_per = personnelSet.getString("Carte");
                        photo = personnelSet.getString("Photo");
                        nomenclature = personnelSet.getString("Nom") + " " + personnelSet.getString("Prenom");

                        // liste_personne.add(bd_per);
                        for (String ar : personne_nivoaka) {
                            if (!bd_per.equalsIgnoreCase(ar)) {
                                System.out.println(bd_per);
                                lazyPersonnInfo = new String[] { photo, nomenclature, "départ" };
                                pub.add(lazyPersonnInfo);
                            }
                        }

                    }
                    publish(pub);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return null;

            }

            @Override
            protected void process(List<ArrayList<String[]>> chunks) {

                // ? Récupérer le dernier élement du tableau
                for(String[]lazy:chunks.get(chunks.size()-1)){
                    model1.addElement(new LazyWorker(lazy[0], lazy[1], lazy[2]));
                    model2.addElement(new LazyWorker(lazy[0], lazy[1], lazy[2]));
                }
            
            }
        };

        worker.execute();
    }

}
