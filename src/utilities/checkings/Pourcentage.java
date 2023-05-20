package utilities.checkings;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;
import utilities.Sary;

public class Pourcentage extends JPanel{
    
    private String icon,titre,taux;
    private Labels name, percentage;

    public Pourcentage(String icon,String titre, String taux) {
        this.icon = icon;
        this.titre = titre;
        this.taux = taux;

        this.setBackground(Colors.backgrounds);
        this.setBorder(new FlatLineBorder(new Insets(5,10,0,10), Colors.stroke, 1, 20));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(profile());
    }

    private JPanel profile(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel icon = new JLabel(new ImageIcon(new Sary().Resize("img/"+this.icon, 50, 50)));
        
        JPanel namePanel = new JPanel(new VerticalFlowLayout());
         name  = new Labels(this.titre,Fonts.textFont,Colors.text,15);
         percentage = new Labels(this.taux,Fonts.textFont,Colors.dark_purple,30);

        //Customization
        percentage.setFont(new Font(Fonts.textFont,Font.BOLD,30));
        percentage.setForeground(Colors.dark_purple);
        name.setFont(new Font(Fonts.textFont,Font.BOLD,15));
        name.setForeground(Colors.text);
        panel.setOpaque(false);namePanel.setOpaque(false);

        //Laying out elements
        namePanel.add(name);namePanel.add(percentage);

        panel.add(icon);
        panel.add(namePanel);

        return panel;

    }
    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Labels getNam() {
        return name;
    }

    public void setName(Labels name) {
        this.name = name;
    }

    public Labels getPercentage() {
        return percentage;
    }

    public void setPercentage(Labels percentage) {
        this.percentage = percentage;
    }
    
    
}
