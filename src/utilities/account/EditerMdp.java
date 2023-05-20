package utilities.account;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.event.MouseInputAdapter;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.Buttons;
import utilities.Colors;
import utilities.Labels;
import utilities.Sary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;

public class EditerMdp extends JFrame {
    private JPasswordField passNow=new JPasswordField();
    private JLabel eye=new JLabel();
    private Labels labely= new Labels("Entrer ton mot de passe actuel","Arial",Colors.text,13);
    private  Buttons cancel=new Buttons("Annuler");
    private  Buttons suivant =new Buttons("Suivant");
    private JPanel panel;
    private Buttons valider=new Buttons("Valider");
    private Labels statut=new Labels("","Arial",Color.red,13);

    public EditerMdp(){
        super("Edit Pass");
        
            this.setSize(400, 300);
            this.setResizable(false);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
    
            this.setLayout(new BorderLayout());
            panel=(JPanel) this.getContentPane();
            panel.add(panTop(),BorderLayout.NORTH);
            panel.add(panMiddle(),BorderLayout.CENTER);
            panel.add(panBottom(),BorderLayout.SOUTH);
    
           

        
       
    }
    public JPanel panTop(){
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
            top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            top.setBackground(Colors.blue);
            Labels titre = new Labels("Modifie mot de passe", "Arial", Colors.backgrounds, 20);
            top.add(titre);
            return top;
    }

    public JPanel panMiddle(){
        JPanel pan=new JPanel();
        //Mot de passe actuel
        JPanel panely=new JPanel(new VerticalFlowLayout());
        JPanel panFieldAndEye=new JPanel(new FlowLayout(FlowLayout.LEFT));
       
        eye.setIcon(new ImageIcon(new Sary().Resize("img/Hide.png", 25, 25)));
        panely.setOpaque(false);
        
        panFieldAndEye.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));
        passNow.setPreferredSize(new Dimension(150,30));
        passNow.setBorder(null);
        passNow.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               statut.setText("");
            }
        });
        eye.setOpaque(false);

        panFieldAndEye.add(passNow);
        panFieldAndEye.add(eye);
        panFieldAndEye.setBackground(Color.white);

        panely.add(labely);
        panely.add(panFieldAndEye);
        panely.add(statut);
        pan.add(panely);

        return pan;
    }
    public JPanel panBottom() {
    JPanel pan=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pan.setOpaque(false);

    cancel.isOutlined(true);
    cancel.addMouseListener(new MouseInputAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            EditerMdp.this.dispose();
        }
    });
   
    
    pan.add(suivant);pan.add(cancel);

    return pan;
    }

    public void ChangeBtn(){
        panel.remove(panBottom());
        JPanel pan=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pan.add(cancel);
        pan.add(valider);
        panel.add(pan,BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }
    
    public JPasswordField getPassNow() {
        return passNow;
    }
    public void setPassNow(JPasswordField passNow) {
        this.passNow = passNow;
    }
    public JLabel getEye() {
        return eye;
    }
    public void setEye(JLabel eye) {
        this.eye = eye;
    }
    public Labels getLabely() {
        return labely;
    }
    public void setLabely(Labels labely) {
        this.labely = labely;
    }
    public Buttons getSuivant() {
        return suivant;
    }
    public void setSuivant(Buttons suivant) {
        this.suivant = suivant;
    }
    public Buttons getValider() {
        return valider;
    }
    public void setValider(Buttons valider) {
        this.valider = valider;
    }
    public Labels getStatut() {
        return statut;
    }
    public void setStatut(Labels statut) {
        this.statut = statut;
    }
    
    

}
