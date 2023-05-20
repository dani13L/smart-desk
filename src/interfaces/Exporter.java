package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.Buttons;
import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;

public class Exporter extends JPanel{
    private Buttons exporterBtn=new Buttons("Exporter");
    Labels titre = new Labels("Exporter",Fonts.textFont,Colors.text,25);
    private Labels language = new Labels("Exporter la liste des personnels", Fonts.textFont, Colors.text, 15);

    public Exporter(){
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
        JPanel panBtn=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panBtn.setOpaque(false);
        panel.setLayout(layout);

        panel.setOpaque(false);

        //-----******************* LABELS ************-*--------

   
        //-----******************* Bouton ************-*--------
        panBtn.add(exporterBtn);
    
        panel.add(language);
        panel.add(panBtn);
        
        return panel;
    }
    public Buttons getExporterBtn() {
        return exporterBtn;
    }
    public void setExporterBtn(Buttons exporterBtn) {
        this.exporterBtn = exporterBtn;
    }
    public Labels getTitre() {
        return titre;
    }
    public void setTitre(Labels titre) {
        this.titre = titre;
    }
    public Labels getLanguage() {
        return language;
    }
    public void setLanguage(Labels language) {
        this.language = language;
    }
    
    
}
