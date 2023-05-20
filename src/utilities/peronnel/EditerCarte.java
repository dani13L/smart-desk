package utilities.peronnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.jdesktop.swingx.table.LabelProperties;

import com.intellij.openapi.ui.VerticalFlowLayout;

import interfaces.Template;
import tasks.WebSocket;
import utilities.Buttons;
import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;
import utilities.Sary;

public class EditerCarte extends JFrame{

    private String carte ="";
    private Labels state;
    Buttons cancel,changer;

    private boolean detecting = true;
    public EditerCarte(){

        this.setTitle("Changer de carte");
        this.setSize(500,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        this.setLayout(new BorderLayout());
        this.add(top(),BorderLayout.NORTH);
        this.add(middle(),BorderLayout.CENTER);
        this.add(bottom(),BorderLayout.SOUTH);

        scanForBadge();

        changer.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseClicked(e);
                detecting = false;
                dispose();
            }
        });

        cancel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseClicked(e);
                detecting = false;
                dispose();
            }
            
        });

    }

    private void scanForBadge(){
        detecting = true;

        if(!WebSocket.badge.equalsIgnoreCase("")){
            WebSocket.badge = "";
        }

        SwingWorker worker = new SwingWorker<Void,Void>(){

            @Override
            protected Void doInBackground() throws Exception {

                boolean already_exists = false;
                ResultSet personSet;

                while(detecting){

                    Thread.sleep(50);

                    // * Si il y a un badge qui passe
                    if(!WebSocket.badge.equalsIgnoreCase("")){

                        carte = WebSocket.badge;
                        personSet = Template.db_tables.getTablePersonnel().select(carte);
                        while(personSet.next()){
                            already_exists = true;
                        }

                        if(already_exists){
                            state.setText("Cette carte est déjà utilisée, utilise une carte encore disponible");
                            already_exists = false;
                        }else{
                            state.setText("Carte enregistrée");
                            System.out.println("New card here");
                        }

                        WebSocket.badge = "";
                    }
                    
                }
                return null;
            }

        };
        worker.execute();
    }



    private Component bottom() {
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(Color.white);
        cancel = new Buttons("Annuler");
        cancel.isOutlined(true);
        changer = new Buttons("Changer");
        panel.add(changer);
        panel.add(cancel);
        

        return panel;
    }



    private Component middle() {

        JPanel panel = new JPanel(new VerticalFlowLayout());
        panel.setBackground(Color.white);
        JLabel icon = new JLabel(new ImageIcon(new Sary().Resize("img/hand.png", 200, 200)));
        JPanel statePanel = new JPanel();
        statePanel.setBackground(Color.white);

        state = new Labels("En attente...",Fonts.textFont,Colors.text,15);

        JPanel overall = new JPanel();
        overall.setBackground(Color.white);

        statePanel.add(state);

        panel.add(icon);
        panel.add(statePanel);

        overall.add(panel);

        return overall;
    }



    private Component top() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.setBackground(Colors.blue);
        Labels titre = new Labels("Passez la nouvelle carte","Arial",Colors.backgrounds,20);
        top.add(titre);
        return top;
    }



    public String getCarte() {
        return carte;
    }
    public void setCarte(String carte) {
        this.carte = carte;
    }



    public Labels getCarteState() {
        return state;
    }



    public void setCarteState(Labels state) {
        this.state = state;
    }
    
}
