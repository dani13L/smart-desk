package utilities.peronnel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.util.ActionCallback.Done;

import interfaces.Personnels;
import utilities.Colors;
import utilities.Fonts;
import utilities.ImageProfile;

public class WorkerCard extends JPanel {

    private String photo,nom,prenom,poste;
    private JLabel name,travail,icon;
    private int id;
    

    public WorkerCard(String photo,String nom,String prenom,String poste,int id){

        this.photo = photo;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.id = id;
        
        this.setBackground(Colors.backgrounds);
        this.setBorder(new FlatLineBorder(new Insets(5,0,5,0), Colors.stroke, 0, 20));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(profile());
    }

    private JPanel profile(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        icon = new JLabel();
        icon.setIcon(new ImageIcon(new ImageProfile().ResizeCercle("img/workers/"+ this.photo, 50, 50, Colors.stroke)));
        //changerImage(this.photo,null);
        
        JPanel namePanel = new JPanel(new VerticalFlowLayout());
        this.name  = new JLabel(this.nom +" "+this.prenom);
        this.travail = new JLabel(this.poste);

        //Customization
        travail.setFont(new Font(Fonts.textFont,Font.BOLD,11));
        travail.setForeground(Color.gray);
        name.setFont(new Font(Fonts.textFont,Font.BOLD,15));
        name.setForeground(Colors.text);
        panel.setOpaque(false);namePanel.setOpaque(false);

        //Laying out elements
        namePanel.add(name);namePanel.add(travail);

        panel.add(icon);
        panel.add(namePanel);

        return panel;
    }

    public void changerImage(String new_image,Personnels personnel){
        SwingWorker worker = new SwingWorker<Void,Void>() {

            Image image;
            ImageIcon imageIcon;
            @Override
            protected Void doInBackground() throws Exception {
                image = new ImageProfile().ResizeCercle("img/workers/"+ new_image, 50, 50, Colors.stroke);
                imageIcon = new ImageIcon(image);
                return null;
            }
            @Override
            protected void done() {
                super.done();

                icon.setIcon(imageIcon);
                setPhoto(new_image);

                //Actualisation
                if(personnel != null){
                    personnel.revalidate();
                    personnel.repaint();
                }

            }
        };
        worker.execute();
        
    }
    

    // * Highlightning the background
    public void setActive(boolean state){
        if(state){

            this.setBackground(Colors.purple);
            this.name.setForeground(Colors.backgrounds);
            this.travail.setForeground(Colors.backgrounds);


        }else{

            this.setBackground(Colors.backgrounds);
            this.name.setForeground(Colors.text);
            this.travail.setForeground(Color.gray);

        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public JLabel getWokerName() {
        return name;
    }

    public void setWorkerName(JLabel name) {
        this.name = name;
    }

    public JLabel getTravail() {
        return travail;
    }

    public void setTravail(JLabel travail) {
        this.travail = travail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
