package services;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.event.MouseInputAdapter;

import com.formdev.flatlaf.ui.FlatLineBorder;

import diu.swe.habib.JPanelSlider.JPanelSlider;
import interfaces.Account;
import interfaces.Template;
import utilities.Colors;
import utilities.Labels;
import utilities.Sary;
import utilities.Login.showHidePasse;
import utilities.account.EditerMdp;
import utilities.account.EditerQuestion;

public class AccountService {
    private Account account = new Account();
    private EditerMdp editMdp = new EditerMdp();;
    private EditerQuestion editQs = new EditerQuestion();

    private boolean testEyes = false;
    private int count = 0;
    private String userName="";

    public AccountService() {
        // edit username
        // ************************************************************************************************************
        account.getEditUsername().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (count == 0) {
                    account.getEditUsername().setIcon(new ImageIcon(new Sary().Resize("img/valider_btn.png", 25, 25)));
                    account.getFieldUsername().setFocusable(true);
                    account.getFieldUsername()
                            .setBorder(new FlatLineBorder(new Insets(4, 10, 4, 10), Colors.purple, 1, 20));
                    count = 1;
                    userName=account.getFieldUsername().getText();
                } else if (count == 1) {
                    modifieUsername();
                    count = 0;
                }

            }
        });

        // edit mot de passe
        // ************************************************************************************************************
        account.getEditPass().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                editMdp.setVisible(true);
            }
        });

        // suivant
        editMdp.getSuivant().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifiePass(editMdp.getPassNow(), editMdp.getStatut(), "password");
            }
        });
        // validation changement mot de passe
        editMdp.getValider().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modifiePass();
            }
        });
        // eye
        editMdp.getEye().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (testEyes) {
                    showHidePasse.showPass(editMdp.getPassNow(), editMdp.getEye());
                    testEyes = false;
                } else if (!testEyes) {
                    showHidePasse.hidePass(editMdp.getPassNow(), editMdp.getEye());
                    testEyes = true;

                }
            }
        });
        // edit
        // Reponse************************************************************************************************************
        account.getEditSQ().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editQs.setVisible(true);

            }
        });

        // suivant
        editQs.getSuivant().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifiePass(editQs.getPassNow(), editQs.getStatut(), "question");

            }
        });

        // valider
        editQs.getValider().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modifieReponse();

            }
        });
        // eye
        editQs.getEye().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (testEyes) {
                    showHidePasse.showPass(editQs.getPassNow(), editQs.getEye());
                    testEyes = false;
                } else if (!testEyes) {
                    showHidePasse.hidePass(editQs.getPassNow(), editQs.getEye());
                    testEyes = true;

                }
            }
        });

        //LogOut----------------------------------------------------------------------------------------------------------
        account.getLogout().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Template.autoriseLogOut=true;
            }
        });

    }

    // methode pour verifier le mot de passe actuel
    public void verifiePass(JPasswordField field, Labels statut, String mdpOrQs) {
        String pass = userNameToPass(Account.fieldUsername.getText());
        String passNow = new String(field.getPassword());
        if (passNow.equals("")) {
            statut.setText("Remplir le champ");
        } else if (passNow.equals(pass)) {
            if (mdpOrQs == "password") {
                editMdp.getStatut().setText("");
                editMdp.getPassNow().setText("");
                editMdp.getLabely().setText("entrer le nouveau mot de passe");
                editMdp.ChangeBtn();
            } else {
                editQs.getPanMiddle().nextPanel(2, editQs.getPanQs(), JPanelSlider.left);
                editQs.ChangeBtn();
            }

        } else {
            statut.setText("verifier votre mot de passe");
            System.out.println("pass:"+pass);
        }
    }

    // methode pour modifier le username
    public void modifieUsername() {
        if (account.getFieldUsername().getText().equals("")) {
            account.getFieldUsername().setBorder((new FlatLineBorder(new Insets(4, 10, 4, 10), Color.red, 1, 20)));
        } else if (userNameInUse(account.getFieldUsername().getText())) {
            account.getEditUsername().setIcon(new ImageIcon(new Sary().Resize("img/edit_blue.png", 25, 25)));
            account.getFieldUsername().setFocusable(false);
            account.getFieldUsername().setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
            JOptionPane.showMessageDialog(null, "Username dejà urtilisé","erreur de modification", JOptionPane.OK_OPTION);

        } else if (!userNameInUse(account.getFieldUsername().getText())) {
            try {
                Template.db_tables.getUtilisateur().modifierUsername(userName,
                        account.getFieldUsername().getText());
                JOptionPane.showMessageDialog(null, "Modifié avec succès");
                account.getEditUsername().setIcon(new ImageIcon(new Sary().Resize("img/edit_blue.png", 25, 25)));
                account.getFieldUsername().setFocusable(false);
                account.getFieldUsername().setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // methode pour modifier le mot de passe
    public void modifiePass() {
        String pass = new String(editMdp.getPassNow().getPassword());
        if (pass.equals("")) {
            editMdp.getStatut().setText("Remplir le champ");
        } else {
            try {
                int id = userNameToId(Account.fieldUsername.getText());
                Template.db_tables.getUtilisateur().modifierPass(id, pass);
                JOptionPane.showMessageDialog(null, "Modifier avec succès");
                editMdp.dispose();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    // methode pour modifier la réponse d'authentication
    public void modifieReponse() {
        if (editQs.getFieldQs().getText().equals("")) {
            editQs.getStatut().setText("Remplir le champ");
        } else {
            editQs.getStatut().setText("");
            Template.db_tables.getUtilisateur().modifierAns(Account.fieldUsername.getText(),
                    editQs.getFieldQs().getText());
            editQs.dispose();
            JOptionPane.showMessageDialog(null, "Modifier avec succès");
        }
    }

    // methode pour avoir le mot de passe à partir de username
    public String userNameToPass(String username) {
        String pass = "";
        try {
            ResultSet res = Template.db_tables.getUtilisateur().select();
            while (res.next()) {
                if (res.getString("UserName").equals(username)) {
                    pass = res.getString("Mdp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pass;
    }

    // methode pour avoir l'id à partir de username
    public int userNameToId(String username) {
        int id = -1;
        ResultSet res = null;
        try {
            res = Template.db_tables.getUtilisateur().select();
            while (res.next()) {
                if (res.getString("UserName").equals(username)) {
                    id = res.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("erreur de transformation to id");
            e.printStackTrace();
        }
        return id;
    }

    // methode pour savoir si l'username est déja utilisé
    public boolean userNameInUse(String username) {
        boolean result = false;
        try {
            ResultSet res = Template.db_tables.getUtilisateur().select();
            while (res.next()) {
                if (res.getString("UserName").equals(username)) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}