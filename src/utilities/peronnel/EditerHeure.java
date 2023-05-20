package utilities.peronnel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.intellij.openapi.ui.VerticalFlowLayout;

import interfaces.Template;
import tasks.DataModifiable;
import utilities.Buttons;
import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;
import utilities.ajouter.ComboHours;

public class EditerHeure extends JFrame {
    JPanel content = (JPanel) this.getContentPane();

    // RadioButton
    JRadioButton varie = new JRadioButton("Variée");
    JRadioButton fixée = new JRadioButton("Fixée");
    JRadioButton continu = new JRadioButton("Continu");
    JRadioButton _pause = new JRadioButton("Avec une pause");

    ButtonGroup bg = new ButtonGroup();
    ButtonGroup bg1 = new ButtonGroup();

    // ComboHours
    ComboHours debut = new ComboHours();
    ComboHours pause = new ComboHours();
    ComboHours reprise = new ComboHours();
    ComboHours fin = new ComboHours();

    // Boutons
    Buttons cancel;
    Buttons changer;

    public static boolean value_changed = false;

    public EditerHeure() {

        // Radio button
        bg1.add(fixée);
        bg1.add(varie);
        bg.add(continu);
        bg.add(_pause);

        // Initialiser combohour

        // ? Charger les placeholders
        if (Template.db_tables.getHeureContinue()
                .verifier(Template.db_tables.getTablePersonnel().select_card_by_id(DataModifiable.id))) {

            debut.getHours().getHourPicker().setText(Template.db_tables.getHeureContinue().getHE());
            fin.getHours().getHourPicker().setText(Template.db_tables.getHeureContinue().getHS());

        } else if (Template.db_tables.getDiscontinue_double()
                .verifier(Template.db_tables.getTablePersonnel().select_card_by_id(DataModifiable.id))) {

            debut.getHours().getHourPicker().setText(Template.db_tables.getDiscontinue_double().getHE_1());
            fin.getHours().getHourPicker().setText(Template.db_tables.getDiscontinue_double().getHS_2());
            pause.getHours().getHourPicker().setText(Template.db_tables.getDiscontinue_double().getHS_1());
            reprise.getHours().getHourPicker().setText(Template.db_tables.getDiscontinue_double().getHE_2());

        }

        fixée.setFont(new Font("Century Gothic", Font.BOLD, 14));
        fixée.setForeground(Colors.text);
        varie.setFont(new Font("Century Gothic", Font.BOLD, 14));
        varie.setForeground(Colors.text);
        _pause.setFont(new Font("Century Gothic", Font.BOLD, 14));
        _pause.setForeground(Colors.text);
        continu.setFont(new Font("Century Gothic", Font.BOLD, 14));
        continu.setForeground(Colors.text);

        this.setTitle("Changer de tranche horaire");
        this.setSize(500, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        this.add(top(), BorderLayout.NORTH);
        this.add(middle(), BorderLayout.CENTER);
        this.add(bottom(), BorderLayout.SOUTH);

        actions();
        continu.setSelected(true);
        varie.setSelected(true);
    }

    // Top panel
    private JPanel top() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.setBackground(Colors.blue);
        Labels titre = new Labels("Choisissez le nouveau tranche horaire", "Arial", Colors.backgrounds, 20);
        top.add(titre);
        return top;
    }

    // Middle Panel
    private JPanel middle() {

        JPanel middle = new JPanel(new VerticalFlowLayout());
        middle.setOpaque(false);

        // ? Radio button panel
        JPanel radio_one = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radio_one.setOpaque(false);
        radio_one.add(fixée);
        radio_one.add(varie);
        middle.add(radio_one);

        // ? Radio button panel
        JPanel radio_two = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radio_two.setOpaque(false);
        radio_two.add(continu);
        radio_two.add(_pause);
        middle.add(radio_two);
        continu.setEnabled(false);
        _pause.setEnabled(false);

        // ? Panel comboHour continu
        JPanel combo_one = new JPanel(new FlowLayout(FlowLayout.LEFT));
        combo_one.setOpaque(false);
        combo_one.add(stack(debut, "Début"));
        combo_one.add(stack(pause, "Pause"));
        pause.getHours().getHourPicker().setEnabled(false);
        pause.getHours().getBouton().setEnabled(false);
        debut.getHours().getHourPicker().setEnabled(false);
        debut.getHours().getBouton().setEnabled(false);
        middle.add(combo_one);

        JPanel combo_two = new JPanel(new FlowLayout(FlowLayout.LEFT));
        combo_two.setOpaque(false);
        combo_two.add(stack(reprise, "Reprise"));
        combo_two.add(stack(fin, "Fin"));
        reprise.getHours().getHourPicker().setEnabled(false);
        reprise.getHours().getBouton().setEnabled(false);
        fin.getHours().getHourPicker().setEnabled(false);
        fin.getHours().getBouton().setEnabled(false);
        middle.add(combo_two);

        return middle;
    }

    // Bottom panel
    private JPanel bottom() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);
        cancel = new Buttons("Annuler");
        cancel.isOutlined(true);
        changer = new Buttons("Changer");
        Labels id = new Labels(String.valueOf(DataModifiable.id), Fonts.textFont, Colors.green, 15);
        panel.add(changer);
        panel.add(cancel);

        return panel;
    }

    // Layout titre/ champ de texte
    private JPanel stack(ComboHours hour, String titre) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textPanel.setOpaque(false);
        Labels title = new Labels(titre, Fonts.textFont, Colors.text, 15);
        textPanel.add(title);
        panel.add(textPanel);
        panel.add(hour);

        return panel;

    }

    private void actions() {
        // Fixée
        fixée.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                continu.setEnabled(true);
                _pause.setEnabled(true);
                if (continu.isSelected()) {
                    debut.getHours().getHourPicker().setEnabled(true);
                    debut.getHours().getBouton().setEnabled(true);
                    fin.getHours().getHourPicker().setEnabled(true);
                    fin.getHours().getBouton().setEnabled(true);
                } else if (_pause.isSelected()) {
                    debut.getHours().getHourPicker().setEnabled(true);
                    debut.getHours().getBouton().setEnabled(true);
                    pause.getHours().getHourPicker().setEnabled(true);
                    pause.getHours().getBouton().setEnabled(true);
                    fin.getHours().getHourPicker().setEnabled(true);
                    fin.getHours().getBouton().setEnabled(true);
                    reprise.getHours().getHourPicker().setEnabled(true);
                    reprise.getHours().getBouton().setEnabled(true);
                }
            }

        });

        // Variee
        varie.addActionListener(e -> {

            continu.setEnabled(false);
            _pause.setEnabled(false);
            debut.getHours().getHourPicker().setEnabled(false);
            debut.getHours().getBouton().setEnabled(false);
            pause.getHours().getHourPicker().setEnabled(false);
            pause.getHours().getBouton().setEnabled(false);
            fin.getHours().getHourPicker().setEnabled(false);
            fin.getHours().getBouton().setEnabled(false);
            reprise.getHours().getHourPicker().setEnabled(false);
            reprise.getHours().getBouton().setEnabled(false);

        });

        // Continu
        continu.addActionListener(e -> {
            debut.getHours().getHourPicker().setEnabled(true);
            debut.getHours().getBouton().setEnabled(true);
            fin.getHours().getHourPicker().setEnabled(true);
            fin.getHours().getBouton().setEnabled(true);
            pause.getHours().getHourPicker().setEnabled(false);
            pause.getHours().getBouton().setEnabled(false);
            reprise.getHours().getHourPicker().setEnabled(false);
            reprise.getHours().getBouton().setEnabled(false);
        });

        // Avec une pause
        _pause.addActionListener(e -> {
            debut.getHours().getHourPicker().setEnabled(true);
            debut.getHours().getBouton().setEnabled(true);
            pause.getHours().getHourPicker().setEnabled(true);
            pause.getHours().getBouton().setEnabled(true);
            fin.getHours().getHourPicker().setEnabled(true);
            fin.getHours().getBouton().setEnabled(true);
            reprise.getHours().getHourPicker().setEnabled(true);
            reprise.getHours().getBouton().setEnabled(true);
        });

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });

        changer.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // ? Enregistrer les heures sélectionnées
                DataModifiable.debut = debut.getHours().getHourPicker().getText();
                DataModifiable.pause = pause.getHours().getHourPicker().getText();
                DataModifiable.reprise = reprise.getHours().getHourPicker().getText();
                DataModifiable.fin = fin.getHours().getHourPicker().getText();

                if (fixée.isSelected()) {

                    if (continu.isSelected()) {

                        String carte = "";
                        // * Prendre la carte de la personne selectionnée
                        carte = Template.db_tables.getTablePersonnel().select_card_by_id(DataModifiable.id);

                        //? Vérifier la personne est dans la table des heures continues
                        if (Template.db_tables.getHeureContinue().verifier(carte)) {
                            
                            // Remplacer l'heure de la personne dans la base de donnée 
                            Template.db_tables.getHeureContinue().modifier_Heures(carte, DataModifiable.debut,
                                    DataModifiable.fin);
                            System.out.println("Heure continue modifiée");

                        // ? Vérifier si la personne est dans la tables des heures avec une pause
                        } else if (Template.db_tables.getDiscontinue_double().verifier(carte)) {

                            // Supprimer la personne de la base
                            Template.db_tables.getDiscontinue_double().supprimer(carte);

                            // * Ajouter la personne dans la base des heures continue
                            Template.db_tables.getHeureContinue().ajouter(carte, DataModifiable.debut, DataModifiable.fin);
                        }

                    } else if (_pause.isSelected()) {

                        String carte = "";
                        // * Prendre la carte de la personne selectionnée
                        carte = Template.db_tables.getTablePersonnel().select_card_by_id(DataModifiable.id);

                        //? Vérifier la personne est dans la table des heures continues
                        if (Template.db_tables.getHeureContinue().verifier(carte)) {

                            // Supprimer la personne de la base
                            Template.db_tables.getHeureContinue().supprimer(carte);
                            
                            // * Ajouter la personne dans la base des heures continue
                            Template.db_tables.getDiscontinue_double().ajouter(carte, DataModifiable.debut, DataModifiable.reprise,DataModifiable.pause,DataModifiable.fin);                 

                        // ? Vérifier si la personne est dans la tables des heures avec une pause
                        } else if (Template.db_tables.getDiscontinue_double().verifier(carte)) {

                            // Remplacer l'heure de la personne dans la base de donnée 
                            Template.db_tables.getDiscontinue_double().modifier_heures(carte, DataModifiable.debut,
                                    DataModifiable.reprise,DataModifiable.pause,DataModifiable.fin);
                        }
                        
                    }
                    EditerHeure.value_changed = true;
                }

                DataModifiable.nouveau_data = true;
            }

        });

    }
}
