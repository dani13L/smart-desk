package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;
public class Apropos extends JPanel {
    private Labels titre = new Labels("A propos",Fonts.textFont,Colors.text, 25);
    public Apropos(){
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(15,10,10,10));
        this.setOpaque(false);
        this.add(titre(),BorderLayout.NORTH);
        this.add(settings(),BorderLayout.CENTER);

    }
    private JPanel titre(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panel.add(titre);
        panel.setOpaque(false);

        return panel;
    }
     //Contenu du parametre
     private JPanel settings(){

        VerticalFlowLayout layout = new VerticalFlowLayout();
        layout.setHorizontalFill(true);

        // *********************** PANELS ************************

        JPanel panel = new JPanel();
        panel.setLayout(layout);

        panel.setOpaque(false);

        //-----******************* LABELS ************-*--------

        Labels mini_Labels = new Labels("Marik'Ora", Fonts.textFont, Colors.purple, 20);
        JTextArea apropos = new JTextArea("Marik'Ora est un logiciel créer et développer par l'entreprise SMART TEKNOLJIA. Il est accompagné par un gadget pour capter le signal des badges électronique,\nutilisés pour pointer les heures d'arriver et les heures de sorties des employés \nPour plus d'information veuillez consulter notre site web: https\\...........");
        apropos.setEditable(false);
        apropos.setBackground(Colors.backgrounds);
   
        panel.add(mini_Labels);panel.add(apropos);
        return panel;
    }
    public Labels getTitre() {
        return titre;
    }
    public void setTitre(Labels titre) {
        this.titre = titre;
    }
    
}
