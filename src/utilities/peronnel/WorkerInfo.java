package utilities.peronnel;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.formdev.flatlaf.ui.FlatLineBorder;

import tasks.DataModifiable;
import utilities.Colors;
import utilities.Fonts;
import utilities.ImageProfile;
import utilities.Sary;

public class WorkerInfo extends JPanel {

    private String photo, nom, prenom;
    private JLabel noms, pre_nom, icon;
    private JLabel remove, edit;
    private JTextField field_nom, field_prenom;
    private boolean editable;
    Path path = FileSystems.getDefault().getPath("").toAbsolutePath();

    public WorkerInfo(String photo, String nom, String prenom) {

        this.photo = photo;
        this.nom = nom;
        this.prenom = prenom;

        this.setBackground(Colors.text);
        this.setBorder(new FlatLineBorder(new Insets(5, 0, 5, 0), Colors.stroke, 0, 20));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(profile());
    }

    private JPanel profile() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JPanel iconPanel = new JPanel();
        iconPanel.setOpaque(false);

        // ? Chargements de l'image dans un thread
        icon = new JLabel();
        loadImage(this.photo);

        // * Panel contenant le nom
        JPanel nomsPanel = new JPanel();
        nomsPanel.setLayout(new BoxLayout(nomsPanel, BoxLayout.Y_AXIS));
        nomsPanel.setOpaque(false);

        JPanel nomPanel = new JPanel();
        JPanel schePanel = new JPanel();
        nomPanel.setOpaque(false);
        schePanel.setOpaque(false);

        // ? Labels pour les noms

        this.noms = new JLabel(this.nom);
        this.pre_nom = new JLabel(this.prenom);

        // * Texfields
        field_nom = new JTextField(this.nom);
        field_prenom = new JTextField(this.prenom);
        field_nom.setBorder(new FlatLineBorder(new Insets(10, 10, 7, 10), Colors.light_grey, 2, 20));
        field_prenom.setBorder(new FlatLineBorder(new Insets(10, 10, 7, 10), Colors.light_stroke, 2, 20));
        field_nom.setBackground(Colors.dark_transparent);
        field_prenom.setBackground(Colors.dark_transparent);
        field_nom.setFont(new Font(Fonts.textFont, Font.BOLD, 15));
        field_prenom.setFont(new Font(Fonts.textFont, Font.BOLD, 15));
        field_nom.setForeground(Colors.backgrounds);
        field_prenom.setForeground(Colors.backgrounds);
        field_nom.setCaretColor(Colors.backgrounds);
        field_prenom.setCaretColor(Colors.backgrounds);
        field_nom.setPreferredSize(new Dimension(200, 40));
        field_prenom.setPreferredSize(new Dimension(200, 40));

        // ? KeyListener des textFields
        field_nom.addKeyListener(inform_new_data());
        field_prenom.addKeyListener(inform_new_data());

        // Customization
        pre_nom.setFont(new Font(Fonts.textFont, Font.BOLD, 15));
        pre_nom.setForeground(Colors.backgrounds);
        noms.setFont(new Font(Fonts.textFont, Font.BOLD, 15));
        noms.setForeground(Colors.backgrounds);

        // Laying out elements
        nomPanel.add(noms);
        nomPanel.add(field_nom);
        schePanel.add(pre_nom);
        schePanel.add(field_prenom);
        field_nom.setVisible(false);
        field_prenom.setVisible(false);

        nomsPanel.add(nomPanel);
        nomsPanel.add(schePanel);
        iconPanel.add(icon);

        panel.add(actions());
        panel.add(iconPanel);
        panel.add(nomsPanel);

        return panel;
    }

    // Panel des boutons modifier et supprimer
    private JPanel actions() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        remove = new JLabel(new ImageIcon(new Sary().Resize("img/remove.png", 30, 30)));
        edit = new JLabel(new ImageIcon(new Sary().Resize("img/edit.png", 30, 30)));

        remove.setToolTipText("Retirer de la liste des peronnels");
        edit.setToolTipText("Modifier");

        // remove.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        // edit.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        icon.addMouseListener(rendreImageChangeable());

        panel.add(edit);
        panel.add(remove);
        return panel;
    }

    // Permetre à l'utilisateur d'éditer
    public void setEditable(boolean editable) {

        // ? Activer les champs de textes si editables
        if (editable) {

            this.editable = true;
            noms.setVisible(false);
            pre_nom.setVisible(false);
            field_nom.setVisible(true);
            field_prenom.setVisible(true);

            icon.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // ? Faire de la photo changeable

        } else {

            this.editable = false;
            noms.setVisible(true);
            pre_nom.setVisible(true);
            field_nom.setVisible(false);
            field_prenom.setVisible(false);
            icon.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    // * Charger les informations dans les textFields
    public void importData(String nom, String prenom) {
        field_nom.setText(nom);
        field_prenom.setText(prenom);
    }

    public boolean isEditable() {
        return this.editable;
    }

    // * Permettre au photo de changer
    public MouseAdapter rendreImageChangeable() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // ? Ouvrir le chargeur de fichier du système
                if (isEditable()) {

                    FileDialog dialog = new FileDialog(DataModifiable.frame, "Choisi une image", FileDialog.LOAD);
                    dialog.setFile("*.jpg;*.jpeg;*.png;*.gif");
                    dialog.setVisible(true);

                    String file = dialog.getFile();
                    String dir = dialog.getDirectory();

                    if (file == null) {
                        DataModifiable.photo = photo;
                    } else {
                        String ic = dir + file;
                        File img = new File(ic);
                        DataModifiable.photo = img.getName();

                        try {

                            Files.copy(img.toPath(),
                                    (new File(path + File.separator + "img" + File.separator + "workers"
                                            + File.separator + img.getName()).toPath()),
                                    StandardCopyOption.REPLACE_EXISTING);

                        } catch (IOException e1) {

                            e1.printStackTrace();

                        }
                    }
                    DataModifiable.nouveau_data = true;
                }

            }
        };

        return adapter;
    }

    // * Informer une nouvelle modification
    public KeyAdapter inform_new_data() {
        KeyAdapter adapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                DataModifiable.nouveau_data = true;
            }
        };

        return adapter;
    }

    public void changePhoto(String nom_photo) {
        loadImage(nom_photo);
    }

    // * Chargement d'image en arrière plan
    private void loadImage(String nom_photo) {
        SwingWorker worker = new SwingWorker<Void, Void>() {

            Image image;
            ImageIcon imageIcon;

            @Override
            protected Void doInBackground() throws Exception {
                image = new ImageProfile().ResizeCercle("img/workers/" + nom_photo, 100, 100, Colors.stroke);
                imageIcon = new ImageIcon(image);
                return null;
            }

            @Override
            protected void done() {
                super.done();

                icon.setIcon(imageIcon);
                setPhoto(nom_photo);
            }
        };
        worker.execute();

    }

    // ! Getter and Setter
    public JTextField getField_nom() {
        return field_nom;
    }

    public void setField_nom(JTextField field_nom) {
        this.field_nom = field_nom;
    }

    public JTextField getField_prenom() {
        return field_prenom;
    }

    public void setField_prenom(JTextField field_prenom) {
        this.field_prenom = field_prenom;
    }

    public JLabel getRemove() {
        return remove;
    }

    public void setRemove(JLabel remove) {
        this.remove = remove;
    }

    public JLabel getEdit() {
        return edit;
    }

    public void setEdit(JLabel edit) {
        this.edit = edit;
    }

    public JLabel getnoms() {
        return this.noms;
    }

    public void setnoms(JLabel noms) {
        this.noms = noms;
    }

    public JLabel getSchedule() {
        return pre_nom;
    }

    public void setSchedule(JLabel pre_nom) {
        this.pre_nom = pre_nom;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
