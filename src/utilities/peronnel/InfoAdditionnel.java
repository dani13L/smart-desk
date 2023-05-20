package utilities.peronnel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import com.formdev.flatlaf.ui.FlatLineBorder;

import tasks.DataModifiable;
import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;
import utilities.Sary;
import utilities.TextFieldFiltre;
import java.awt.event.*;

public class InfoAdditionnel extends JPanel {

    private String key, value;
    private Labels Lkey, Lvalue;
    private JTextField Fvalue;
    private boolean editable;
    JLabel edit_icon = new JLabel(new ImageIcon(new Sary().Resize("img/edit_blue.png", 25, 25)));

    public InfoAdditionnel(String key, String value, boolean edit_button) {

        this.key = key;
        this.value = value;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        edit_icon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // ? les valeurs

        Lkey = new Labels(this.key + ":", Fonts.textFont, Colors.text, 15);
        Lvalue = new Labels(this.value, Fonts.textFont, Colors.text, 15);
        Fvalue = new JTextField(12);
        Fvalue.setBackground(Colors.dark_transparent);
        Fvalue.setBorder(new FlatLineBorder(new Insets(10, 10, 7, 10), Colors.grey, 2, 20));
        Fvalue.setFont(new Font(Fonts.textFont, Font.BOLD, 15));
        Fvalue.setPreferredSize(new Dimension(167, 40));

        this.setOpaque(false);
        this.add(Lkey);

        //
        if (Lkey.getText().contains("CIN")) {
            PlainDocument docCIN = (PlainDocument) Fvalue.getDocument();
		    docCIN.setDocumentFilter(new TextFieldFiltre());
            this.add(Box.createHorizontalStrut(10));
            this.add(Lvalue);
        } else {
            this.add(Lvalue);
        }

        // * Différent action pour chaque bouton modifier
        if (Lkey.getText().contains("Heure")) {

            edit_icon.addMouseListener(editShift());

        } else if (Lkey.getText().contains("Carte")) {

            edit_icon.addMouseListener(editCard());
        }

        if (edit_button) {
            this.add(edit_icon);
        } else {
            this.add(Fvalue);
        }

        // ? Informer le boucle d'enregistrement de modification dans personnelService qu' un nouveau data est en train d'être tapé
        Fvalue.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                DataModifiable.nouveau_data = true;
            }
        });

        edit_icon.setVisible(false);
        Fvalue.setVisible(false);
    }

    // ? Modification de tranche horraire
    public MouseAdapter editShift() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditerHeure edit = new EditerHeure();
                edit.setVisible(true);
            }
        };

        return adapter;

    }

    // ? Modification de Carte
    public MouseAdapter editCard() {
       MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EditerCarte carte = new EditerCarte();
                carte.setVisible(true);
            }
        };

        return adapter;
    }

    public void setEditable(boolean editable) {

        if (editable) {
            this.editable = editable;
            Fvalue.setVisible(true);
            Lvalue.setVisible(false);
            edit_icon.setVisible(true);
        } else {
            this.editable = editable;
            Fvalue.setVisible(false);
            Lvalue.setVisible(true);
            edit_icon.setVisible(false);
        }

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Labels getLvalue() {
        return Lvalue;
    }

    public void setLvalue(Labels lvalue) {
        Lvalue = lvalue;
    }

    public JTextField getFvalue() {
        return Fvalue;
    }

    public void setFvalue(JTextField fvalue) {
        Fvalue = fvalue;
    }

    public Labels getLkey() {
        return Lkey;
    }

    public void setLkey(Labels lkey) {
        Lkey = lkey;
    }

}
