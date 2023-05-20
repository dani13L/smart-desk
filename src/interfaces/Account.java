package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.AsideLayout;
import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;
import utilities.Sary;
import utilities.menu.UserProfile;

public class Account extends JPanel {
    
    UserProfile profile = new UserProfile("user", "Username", "Occupation");

    private JPanel top,center,bottom;

    
    //variable 
    public static  JTextField fieldUsername = new JTextField("Username");
    private JLabel editUsername = new JLabel(new ImageIcon(new Sary().Resize("img/edit_blue.png",25,25)));
    private  JLabel editPass = new JLabel(new ImageIcon(new Sary().Resize("img/edit_blue.png",25,25)));
    private JLabel editSQ = new JLabel(new ImageIcon(new Sary().Resize("img/edit_blue.png",25,25)));

    //LogOut
    private JLabel logout = new JLabel(new ImageIcon(new Sary().Resize("img/getout.png",30,30)));

    Labels usernameLabel,changePass,changeSQ,changeCompte;
    public Account(){
        changePass = new Labels("Changer de compte",Fonts.textFont,Colors.text,15);
        
        this.top = topPanel();
        this.center = centerPanel();
        this.bottom = bottomPanel();
        
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(15,10,10,10));
        this.setOpaque(false);

        
        this.add(this.top,BorderLayout.NORTH);
        this.add(this.center,BorderLayout.CENTER);
        this.add(this.bottom,BorderLayout.SOUTH);
        
    }

    public JPanel topPanel(){
        //Panels
        logout.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));
        AsideLayout aside = new AsideLayout(profile, logout);
        return aside;
    }
    public JPanel centerPanel(){
        JPanel panel = new JPanel(new VerticalFlowLayout());
        panel.setOpaque(false);

        JPanel middelChanges = new JPanel();
        JPanel middle = new JPanel(new VerticalFlowLayout());
        middle.setOpaque(false);
        JPanel changes = new JPanel(new VerticalFlowLayout());
        changes.setBorder(new FlatLineBorder(new Insets(10,10,10,10), Colors.stroke,1,20));

        //Removings
        middelChanges.setOpaque(false);
        changes.setOpaque(false);

        //username label
        usernameLabel = new Labels("Nom d'utilisateur","Arial",Colors.text,15);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
        //Edit logo
        editUsername.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        
        //Changind name textfield
        fieldUsername.setPreferredSize(new Dimension(150,40));
        fieldUsername.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        fieldUsername.setFocusable(false);
        
        //textfield layout
        JPanel fieldLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldLayout.setOpaque(false);
        fieldLayout.add(fieldUsername);fieldLayout.add(editUsername);
       // AsideLayout fieldLayout = new AsideLayout(field, edit);

        //Password label
        changePass = new Labels("Changer de mot de passe","Arial",Colors.text,15);
        changePass.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));

        //Edit logo password
       
        editPass.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        //Password layout
      //  JPanel  passChangeLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));
       // passChangeLayout.setOpaque(false);
      //  passChangeLayout.add(changePass); passChangeLayout.add(editPass);
       AsideLayout passChangeLayout = new AsideLayout(changePass, editPass);

        //Security question label
        changeSQ = new Labels("Changer de question de sécurité","Arial",Colors.text,15);
        changeSQ.setBorder(BorderFactory.createEmptyBorder(3, 2, 0, 0));

        //Edit logo password

        editSQ.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        //Password layout
        AsideLayout passSQLayout = new AsideLayout(changeSQ, editSQ);

        //Addings 
        changes.add(usernameLabel);changes.add(fieldLayout);
        changes.add(Box.createVerticalStrut(10));
        changes.add(passChangeLayout);
        changes.add(Box.createVerticalStrut(10));
        changes.add(passSQLayout);

        middelChanges.add(changes);
        middle.add(middelChanges);

        panel.add(middle);
        return panel;
    }
    public JPanel bottomPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

       changePass.addMouseListener(new MouseInputAdapter() {
            
       });
         changeCompte= new Labels("Changer compte","Arial",Colors.purple,15);
       
        panel.add(changeCompte);
        return panel;
    }

    

    public void load(JPanel ambony,JPanel apovony){

        this.remove(top);
        this.remove(center);

        this.top = ambony;
        this.center = apovony;
        this.add(top,BorderLayout.NORTH);
        this.add(center,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();

    }

  /*   private void change(String text){

        changePass.setText(text);
        changePass.revalidate();
        changePass.repaint();

    }*/

   

   
    public Labels getChangePass() {
        return changePass;
    }

    public void setChangePass(Labels changePass) {
        this.changePass = changePass;
    }

    public JLabel getEditPass() {
        return editPass;
    }

    public void setEditPass(JLabel editPass) {
        this.editPass = editPass;
    }

    public JLabel getEditSQ() {
        return editSQ;
    }

    public void setEditSQ(JLabel editSQ) {
        this.editSQ = editSQ;
    }

    public JLabel getEditUsername() {
        return editUsername;
    }

    public void setEditUsername(JLabel editUsername) {
        this.editUsername = editUsername;
    }

    public JTextField getFieldUsername() {
        return fieldUsername;
    }

    public static void setFieldUsername(JTextField fieldUsername) {
        Account.fieldUsername = fieldUsername;
    }

    public Labels getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Labels usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public Labels getChangeSQ() {
        return changeSQ;
    }

    public void setChangeSQ(Labels changeSQ) {
        this.changeSQ = changeSQ;
    }

    public Labels getChangeCompte() {
        return changeCompte;
    }

    public void setChangeCompte(Labels changeCompte) {
        this.changeCompte = changeCompte;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public JLabel getLogout() {
        return logout;
    }

    public void setLogout(JLabel logout) {
        this.logout = logout;
    }
    


}
