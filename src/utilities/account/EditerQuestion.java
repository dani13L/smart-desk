package utilities.account;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import diu.swe.habib.JPanelSlider.JPanelSlider;
import utilities.Buttons;
import utilities.Colors;
import utilities.Labels;
import utilities.Sary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class EditerQuestion extends JFrame {
    private JPasswordField passNow=new JPasswordField();
    private JLabel eye=new JLabel();
    private Labels labely= new Labels("Entrer ton mot de passe actuel","Arial",Colors.text,13);
    private  Buttons cancel=new Buttons("Annuler");
    private  Buttons suivant =new Buttons("Suivant");
    private Labels statut=new Labels("","Arial",Color.red,13);
    private JPanelSlider panMiddle=new JPanelSlider();
    private String questions[] = { "Quelle est ton surnom?",
            "Quelle est ta couleur preferé?",
            "Quelle est le nom de votre ville natale?",
    };
    private JComboBox<String[]> questionsBox = new JComboBox(questions);
    private JTextField fieldQs=new JTextField();
    private JPanel panQs=panelQuestion();
    private JPanel panel;
    private Buttons valider=new Buttons("Valider");

    public EditerQuestion(){
        super("Edit Autentification");
            this.setSize(400, 300);
            this.setResizable(false);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
    
            this.setLayout(new BorderLayout());
            panel=(JPanel) this.getContentPane();
            panMiddle.setBorder(null);
            panMiddle.setOpaque(false);
            panMiddle.add(panPass());
            panMiddle.add(panQs);
            panel.add(panTop(),BorderLayout.NORTH);
            panel.add(panMiddle,BorderLayout.CENTER);
            panel.add(panBottom(),BorderLayout.SOUTH);
    
            this.addWindowListener(new WindowAdapter(){           
                @Override
                public void windowClosing(WindowEvent e) {
                }
            });
        
       
    }
    public JPanel panTop(){
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
            top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            top.setBackground(Colors.blue);
            Labels titre = new Labels(" Modifie question d'autentification", "Arial", Colors.backgrounds, 20);
            top.add(titre);
            return top;
    }

    public JPanel panPass(){
        JPanel pan=new JPanel();
        //Mot de passe actuel
        JPanel panActuelPass=new JPanel(new VerticalFlowLayout());
        JPanel panFieldAndEye=new JPanel(new FlowLayout(FlowLayout.LEFT));
       
        eye.setIcon(new ImageIcon(new Sary().Resize("img/Hide.png", 25, 25)));
        
 
        panActuelPass.setOpaque(false);
        
        panFieldAndEye.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));
        passNow.setPreferredSize(new Dimension(150,30));
        passNow.setBorder(null);
        eye.setOpaque(false);

        panFieldAndEye.add(passNow);
        panFieldAndEye.add(eye);
        panFieldAndEye.setBackground(Color.white);

        panActuelPass.add(labely);
        panActuelPass.add(panFieldAndEye);
        panActuelPass.add(statut);

        pan.add(panActuelPass);

        return pan;
    }
    public JPanel panelQuestion(){
        JPanel panely=new JPanel();
        JPanel pan = new JPanel(new VerticalFlowLayout());
        Labels fanazavana = new Labels("<html> En cas d'oublie votre mod de pass,"
                + "\n" + "<br> cette question vous permet de le renitialisé</html> ", "Arial", Colors.text, 15);
        questionsBox.setFont(new Font("Arial", Font.PLAIN, 15));
        fieldQs.setPreferredSize(new Dimension(150, 40));
        fieldQs.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 2, 20));
    
        pan.add(fanazavana);
        pan.add(questionsBox);
        pan.add(fieldQs);
        pan.add(statut);
        pan.setPreferredSize(new Dimension(300,300));
        panely.add(pan);
        return panely;

    }
    public JPanel panBottom() {
    JPanel pan=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pan.setOpaque(false);

   cancel.isOutlined(true);
    cancel.addMouseListener(new MouseInputAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            EditerQuestion.this.dispose();
        }
    });

   
    pan.add(suivant); pan.add(cancel);
   

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
    public JPanel getPanQs() {
        return panQs;
    }
    public void setPanQs(JPanel panQs) {
        this.panQs = panQs;
    }
    public Labels getStatut() {
        return statut;
    }
    public void setStatut(Labels statut) {
        this.statut = statut;
    }
    public JPanelSlider getPanMiddle() {
        return panMiddle;
    }
    public void setPanMiddle(JPanelSlider panMiddle) {
        this.panMiddle = panMiddle;
    }
    public JComboBox<String[]> getQuestionsBox() {
        return questionsBox;
    }
    public void setQuestionsBox(JComboBox<String[]> questionsBox) {
        this.questionsBox = questionsBox;
    }
    public JTextField getFieldQs() {
        return fieldQs;
    }
    public void setFieldQs(JTextField fieldQs) {
        this.fieldQs = fieldQs;
    }
    public Buttons getValider() {
        return valider;
    }
    public void setValider(Buttons valider) {
        this.valider = valider;
    }
    
    

}
