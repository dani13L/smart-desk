package interfaces;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import diu.swe.habib.JPanelSlider.JPanelSlider;
import utilities.Buttons;
import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;

public class Creation extends JPanel {
    // create variable
    private JTextField nomField = new JTextField();
    private JTextField prenomField = new JTextField();
    private JTextField field = new JTextField();
    private Buttons next = new Buttons("Suivant");
    private JPasswordField passField = new JPasswordField();
    private JPasswordField confirmField = new JPasswordField();
    private String questions[] = { "Quelle est ton surnom?",
            "Quelle est ta couleur preferé?",
            "Quelle est le nom de votre ville natale?",
    };
    private JComboBox<String[]> questionsBox = new JComboBox(questions);
    private JTextField reponseField = new JTextField();

    public Labels retour = new Labels("Log in", "Arial", Colors.blue, 15);
    private JPanelSlider slider = new JPanelSlider();

    private JPanel creation1 = createPanel(1);
    private JPanel creation2 = createPanel(2);
    private JPanel creation3 = createPanel(3);

    // labels
    private Labels nomLabel, prenomLabel, usernameLabel, passLabel, confirmLabel, fanazavana, titre;

    // getter and setter

    public Creation() {
        setLayout(new VerticalFlowLayout());
        add(topPanelCreate());
        slider.add(creation1);
        slider.add(creation2);
        slider.add(creation3);

        slider.setOpaque(false);
        slider.setBorder(null);
        add(slider);

        JPanel panNext = new JPanel(new FlowLayout());
        next.setPreferredSize(new Dimension(100, 40));
        panNext.add(next);
        add(panNext);
        JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panBtn.add(retour);
        add(panBtn);

    }

    public Labels getNomLabel() {
        return nomLabel;
    }

    public void setNomLabel(Labels nomLabel) {
        this.nomLabel = nomLabel;
    }

    public Labels getPrenomLabel() {
        return prenomLabel;
    }

    public void setPrenomLabel(Labels prenomLabel) {
        this.prenomLabel = prenomLabel;
    }

    public Labels getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Labels usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public Labels getPassLabel() {
        return passLabel;
    }

    public void setPassLabel(Labels passLabel) {
        this.passLabel = passLabel;
    }

    public Labels getConfirmLabel() {
        return confirmLabel;
    }

    public void setConfirmLabel(Labels confirmLabel) {
        this.confirmLabel = confirmLabel;
    }

    public Labels getFanazavana() {
        return fanazavana;
    }

    public void setFanazavana(Labels fanazavana) {
        this.fanazavana = fanazavana;
    }

    public Labels getTitre() {
        return titre;
    }

    public void setTitre(Labels titre) {
        this.titre = titre;
    }

    public JPanel createPanel(int index) {

        JPanel panel = new JPanel(new VerticalFlowLayout());
        panel.setOpaque(false);
        JPanel middelChanges = new JPanel();
        JPanel middle = new JPanel(new VerticalFlowLayout());
        middle.setOpaque(false);
        JPanel changes = new JPanel(new VerticalFlowLayout());
        changes.setBorder(new FlatLineBorder(new Insets(10, 10, 10, 10), Colors.stroke, 1, 20));
        JPanel midButton = new JPanel();

        // Removings
        middelChanges.setOpaque(false);
        midButton.setOpaque(false);
        changes.setBackground(Color.white);

        // -------------- New username--------------
        // index
        // 1**************************************************************************************************************


        // index2**************************************************************************************************************
       

        // index3**************************************************************************************************************
        // label fanazavana

        // Addings
        if (index == 1) {
            // Nom label
            nomLabel = new Labels("Nom", "Arial", Colors.text, 15);
            nomLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
            // prenom Label
            prenomLabel = new Labels("Prénom", "Arial", Colors.text, 15);
            prenomLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
      
            // Changind name textfield
            nomField.setPreferredSize(new Dimension(200, 40));
            nomField.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));

            // Changind name textfield
            prenomField.setPreferredSize(new Dimension(200, 40));
            prenomField.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));

            changes.removeAll();
            changes.add(Box.createVerticalStrut(30));
            changes.add(nomLabel);
            changes.add(nomField);
            changes.add(Box.createVerticalStrut(10));
            changes.add(prenomLabel);
            changes.add(prenomField);
            changes.add(Box.createVerticalStrut(30));

        } else if (index == 2) {
            // username
            usernameLabel=new Labels("Username", "Arial", Colors.text, 15);
            usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
            // Changind name textfield
            field.setPreferredSize(new Dimension(150, 40));
            field.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));

            // -------------- New passWord--------------
            passLabel = new Labels("Créer un mot de passe", "Arial", Colors.text, 15);
            passLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));

            // Changind name textfield
            passField.setPreferredSize(new Dimension(150, 40));
            passField.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));

            // -------------- Confirm passWord--------------
            confirmLabel = new Labels("Confirmer le mot de passe", "Arial", Colors.text, 15);
            confirmLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));

            // Changind name textfield
            confirmField.setPreferredSize(new Dimension(150, 40));
            confirmField.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));
            changes.removeAll();
            changes.add(Box.createVerticalStrut(10));
            changes.add(usernameLabel);
            changes.add(field);
            changes.add(Box.createVerticalStrut(10));
            changes.add(passLabel);
            changes.add(passField);
            changes.add(Box.createVerticalStrut(10));
            changes.add(confirmLabel);
            changes.add(confirmField);
            // changesButton.add(next);

        } else if (index == 3) {
            // question
            questionsBox.setFont(new Font("Arial", Font.PLAIN, 15));
            questionsBox.setPreferredSize(new Dimension(150, 40));

            // Changind name textfield
            reponseField.setPreferredSize(new Dimension(150, 40));
            reponseField.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));
            fanazavana = new Labels("<html> En cas d'oublie votre mod de pass,"
                    + "\n" + "<br> cette question vous permet de le renitialisé</html> ", "Arial", Colors.text, 15);
            changes.removeAll();
            changes.add(fanazavana);
            changes.add(Box.createVerticalStrut(10));
            changes.add(questionsBox);
            changes.add(Box.createVerticalStrut(20));
            changes.add(reponseField);

        }

        middelChanges.add(changes);
        middle.add(middelChanges);

        panel.add(middle);

        return panel;
    }

    public JPanel topPanelCreate() {
        // Panels
        titre = new Labels("Créer un compte", Fonts.textFont, Colors.blue, 25);

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        panel.add(titre);

        return panel;
    }

    // getter and
    // setter********************************************************************************
    public JTextField getField() {
        return field;
    }

    public void setField(JTextField field) {
        this.field = field;
    }

    public JPasswordField getPassField() {
        return passField;
    }

    public void setPassField(JPasswordField passField) {
        this.passField = passField;
    }

    public JPasswordField getConfirmField() {
        return confirmField;
    }

    public void setConfirmField(JPasswordField confirmField) {
        this.confirmField = confirmField;
    }

    public JComboBox getQuestionsBox() {
        return questionsBox;
    }

    public void setQuestionsBox(JComboBox questionsBox) {
        this.questionsBox = questionsBox;
    }

    public Buttons getNext() {
        return next;
    }

    public void setNext(Buttons next) {
        this.next = next;
    }

    public JTextField getReponseField() {
        return reponseField;
    }

    public void setReponseField(JTextField reponseField) {
        this.reponseField = reponseField;
    }

    public JTextField getNomField() {
        return nomField;
    }

    public void setNomField(JTextField nomField) {
        this.nomField = nomField;
    }

    public JTextField getPrenomField() {
        return prenomField;
    }

    public void setPrenomField(JTextField prenomField) {
        this.prenomField = prenomField;
    }

    public JPanelSlider getSlider() {
        return slider;
    }

    public void setSlider(JPanelSlider slider) {
        this.slider = slider;
    }

    public Labels getRetour() {
        return retour;
    }

    public void setRetour(Labels retour) {
        this.retour = retour;
    }

    public JPanel getCreation1() {
        return creation1;
    }

    public void setCreation1(JPanel creation1) {
        this.creation1 = creation1;
    }

    public JPanel getCreation2() {
        return creation2;
    }

    public void setCreation2(JPanel creation2) {
        this.creation2 = creation2;
    }

    public JPanel getCreation3() {
        return creation3;
    }

    public void setCreation3(JPanel creation3) {
        this.creation3 = creation3;
    }

}
