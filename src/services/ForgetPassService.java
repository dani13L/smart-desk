package services;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;
import com.formdev.flatlaf.ui.FlatLineBorder;

import diu.swe.habib.JPanelSlider.JPanelSlider;
import interfaces.ForgetPass;
import interfaces.Login;
import interfaces.Template;
import utilities.Colors;
import utilities.Login.showHidePasse;

public class ForgetPassService {
    private ForgetPass forgetPass = new ForgetPass();
    int id = -1;
    String question, valiny;
    boolean testEyes = false;

    // compterur pour le bouton suivant et retour
    private int countNext = 0;

    public ForgetPassService() {
        // premier next
        forgetPass.getNextForgot1().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (countNext == 0) {
                    forgetPass.getStatutAutentification().setText("");
                    if (forgetPass.getTxtForgotNom().getText().equals("")
                            || forgetPass.getTxtForgotNom().getText().equals("")) {
                        redMarkPan1();
                    } else if (haveAccount()) {
                        forgetPass.getSlider().nextPanel(2, forgetPass.getPanQuestion(), JPanelSlider.right);
                        countNext++;
                    } else {
                        forgetPass.getStatutAutentification().setForeground(Color.red);

                        // pour le
                        // language-------------------------------------------------------------------
                        if (forgetPass.getLabNom().getText().equals("Nom"))
                            forgetPass.getStatutAutentification().setText("Aucune compte trouvée");
                        else if (forgetPass.getLabNom().getText().equals("Anarana"))
                            forgetPass.getStatutAutentification().setText("Tsy manan kaonty io");
                        // -------------------------------------------------------------------------------------
                        redMarkPan1();
                    }
                } else if (countNext == 1) {
                    forgetPass.getStatutQuestion().setText("");
                    if (forgetPass.getTxtReponseForgot().getText().equals("")) {
                        redMarkPan2();
                    }
                    // verifier si la réponse est correct
                    else if (forgetPass.getTxtReponseForgot().getText().equals(valiny)) {
                        forgetPass.getSlider().nextPanel(2, forgetPass.getPanRecoveryPass(), JPanelSlider.right);
                        countNext++;
                        forgetPass.getNextForgot1().setText("Valider");
                    } else {
                        forgetPass.getStatutQuestion().setForeground(Color.red);
                        forgetPass.getStatutQuestion().setText("incorrect");

                    }
                } else if (countNext == 2) {
                    if (correctMdp()) {
                        try {
                            Template.db_tables.getUtilisateur().modifierPass(id,
                                    new String(forgetPass.getTxtNewPass1().getPassword()));
                            System.out.println("Modifier avec succès");
                            countNext = 0;
                            Login.slidePan.remove(1);

                        } catch (SQLException e1) {
                            System.out.println("Eurreur de modification");
                            e1.printStackTrace();
                        }
                    }
                }

            }

        });

        // back
        forgetPass.getBackForgot1().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (countNext == 0) {
                    Login.slidePan.remove(1);
                } else if (countNext == 1) {
                    forgetPass.getSlider().nextPanel(2, forgetPass.getPanAutentification(), JPanelSlider.left);
                    countNext--;
                } else if (countNext == 2) {
                    forgetPass.getSlider().nextPanel(2, forgetPass.getPanQuestion(), JPanelSlider.left);
                    countNext--;
                    forgetPass.getNextForgot1().setText("Suivant");
                }

            }
        });

        // Mot de pass visible et non visible
        forgetPass.getSaryMaso().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (testEyes) {
                    showHidePasse.showPass(forgetPass.getTxtNewPass1(), forgetPass.getSaryMaso());
                    showHidePasse.showPass(forgetPass.getTxtNewPass2(), forgetPass.getSaryMaso());
                    testEyes = false;
                } else {
                    showHidePasse.hidePass(forgetPass.getTxtNewPass1(), forgetPass.getSaryMaso());
                    showHidePasse.hidePass(forgetPass.getTxtNewPass2(), forgetPass.getSaryMaso());
                    testEyes = true;

                }
            }
        });

        // remove red Mark en clickant sur la zone de texte
        removeRedMark();

    }

    // **********************verification
    // nom,prenom,Question***************************************
    public boolean haveAccount() {
        String nomTest, prenomTest;
        boolean test = false;
        ResultSet res = null;
        try {
            res = Template.db_tables.getUtilisateur().select();
            while (res.next()) {
                nomTest = res.getString("Nom");
                prenomTest = res.getString("Prenom");
                if (nomTest.equalsIgnoreCase(forgetPass.getTxtForgotNom().getText())
                        && prenomTest.equalsIgnoreCase(forgetPass.getTxtForgotPrenom().getText())) {
                    id = res.getInt("id");
                    question = res.getString("Question");
                    forgetPass.getLabQuestionForgetPass().setText(question);
                    valiny = res.getString("Reponse");
                    System.out.println("Id=" + id + " question:" + question + " reponse: " + valiny);
                    test = true;
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return test;
    }

    // redMark panel 1
    private void redMarkPan1() {

        if (forgetPass.getTxtForgotNom().getText().equals("")) {
            forgetPass.getTxtForgotNom().setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Color.red, 3, 20));
            forgetPass.getTxtForgotNom().setForeground(Color.red);
            forgetPass.getTxtForgotNom().setText("veuiller remplir");

        }
        if (forgetPass.getTxtForgotPrenom().getText().equals("")) {
            forgetPass.getTxtForgotPrenom().setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Color.red, 3, 20));
            forgetPass.getTxtForgotPrenom().setForeground(Color.red);
            forgetPass.getTxtForgotPrenom().setText("veuiller remplir");
        }
        if (forgetPass.getStatutAutentification().getText().equals("Aucune compte trouvée") ||
                forgetPass.getStatutAutentification().getText().equals("Tsy manan kaonty io")) {
            forgetPass.getTxtForgotNom().setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Color.red, 3, 20));
            forgetPass.getTxtForgotPrenom().setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Color.red, 3, 20));
        }

    }

    // redMark panel2
    private void redMarkPan2() {
        if (forgetPass.getTxtReponseForgot().getText().equals("")) {
            forgetPass.getTxtReponseForgot().setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Color.red, 3, 20));
            forgetPass.getTxtReponseForgot().setForeground(Color.red);
            forgetPass.getTxtReponseForgot().setText("veuiller remplir");
        }
    }

    // redMark panel 3
    private boolean correctMdp() {
        boolean test = false;
        String pass1 = new String(forgetPass.getTxtNewPass1().getPassword());
        String pass2 = new String(forgetPass.getTxtNewPass2().getPassword());
        if (pass1.equals("") || pass2.equals("")) {
            if (pass1.equals("")) {
                forgetPass.getTxtNewPass1().setBorder(new FlatLineBorder(new Insets(12, 10, 12, 10), Color.red, 3, 20));
                forgetPass.getTxtNewPass1().setForeground(Color.red);
            }
            if (pass2.equals("")) {
                forgetPass.getTxtNewPass2().setBorder(new FlatLineBorder(new Insets(12, 10, 12, 10), Color.red, 3, 20));
                forgetPass.getTxtNewPass2().setForeground(Color.red);
            }
        } else if (pass1.equals(pass2)) {
            test = true;
        } else {
            forgetPass.getStatutRecovery().setForeground(Color.red);
            forgetPass.getStatutRecovery().setText("verifier le mot de passe");
            System.out.println("non identique");
        }
        return test;
    }

    // enleve les border rouge sur les zones de texte
    public void removeRedMark() {
        // pour les textField
        ArrayList<JTextField> textField = new ArrayList<JTextField>(Arrays.asList(
                forgetPass.getTxtForgotNom(), forgetPass.getTxtForgotPrenom(), forgetPass.getTxtReponseForgot()));
        for (JTextField txt : textField) {
            txt.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    txt.setBorder(new FlatLineBorder(new Insets(12, 10, 12, 10), Colors.purple, 3, 20));
                    txt.setForeground(Color.DARK_GRAY);
                    txt.setText("");
                }

            });

        }

        // pour les passwordField
        ArrayList<JPasswordField> PassField = new ArrayList<JPasswordField>(Arrays.asList(
                forgetPass.getTxtNewPass1(), forgetPass.getTxtNewPass2()));
        for (JPasswordField txt : PassField) {
            txt.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    txt.setBorder(new FlatLineBorder(new Insets(12, 10, 12, 10), Colors.purple, 3, 20));
                    txt.setForeground(Color.DARK_GRAY);
                }
            });

        }

    }

    public ForgetPass getForgetPass() {
        return forgetPass;
    }

    public void setForgetPass(ForgetPass forgetPass) {
        this.forgetPass = forgetPass;
    }

}
