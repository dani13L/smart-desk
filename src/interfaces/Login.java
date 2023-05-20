package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;
import diu.swe.habib.JPanelSlider.JPanelSlider;
import services.CreationService;
import services.ForgetPassService;
import utilities.AsideLayout;
import utilities.Buttons;
import utilities.Colors;
import utilities.Labels;
import utilities.Sary;

public class Login extends JPanel {
    private static final long serialVersionUID = 1L;
    private  JPanel panDivise = new JPanel();
    // image a gauche
    private JLabel imageAgauche = new JLabel();

    // logo
    private JPanel flowlogo = new JPanel();
    private ImageIcon logo = new ImageIcon(
            new ImageIcon("img/logo.png").getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
    private JLabel labLogo = new JLabel();

    // panels dans Panel creation
    private CreationService creationService=new CreationService();
    private JPanel panelCreation=creationService.getCreation();

    // panels forgot pass
    private ForgetPassService forgetPassService=new ForgetPassService();
    private JPanel panForgotPass=forgetPassService.getForgetPass();

    public JTextField txtUserName = new JTextField();
   
    private Labels labUserName = new Labels("Nom d'utilisateur", "Arial", Colors.text, 15);
    public JPanel panUserName = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel labStatutUserName = new JLabel("");
    public JPanel userNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public JPasswordField txtPass = new JPasswordField();
    private Labels labPass = new Labels("Mot de passe", "Arial", Colors.text, 15);
    private JPanel panTxtPass = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel labStatutTxtPass = new JLabel("");
    
    public  Buttons btnConnect = new Buttons("Se connecter");
    private JPanel panBtnConnect = new JPanel(new FlowLayout());
    private Labels labPhrase = new Labels(" pas de compte?", "Arial", Colors.grey, 11);
    public Labels labCreer = new Labels("Créer un compte", "Arial", Colors.text, 12);
    private Labels labForgotPass = new Labels("Mot de passe oublier?", "Arial", Colors.grey, 12);

    public JLabel statut = new JLabel("");
    private JPanel panStatut = new JPanel(new FlowLayout(FlowLayout.CENTER));
    public JPanel panelAdroite = new JPanel(new FlowLayout());
    public JPanel panelInformation = new JPanel();

    private JPanel panPassOublie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel panSouth = new JPanel(new BorderLayout());
    private JPanel panAmbany = new JPanel(new FlowLayout(FlowLayout.CENTER));

    public JLabel imgVisible = new JLabel();
    private JPanel panEye = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel passPanel = new AsideLayout(txtPass,panEye);

    public static JPanelSlider slidePan = new JPanelSlider();

    JPanel panMiddle=new JPanel(new VerticalFlowLayout());
    
    public Login(){

    }

    public JPanel login() {
       
        panDivise.setLayout(new GridLayout(1, 2));
        
        //Partie Logo
        labLogo.setIcon(logo);
        flowlogo.setLayout(new FlowLayout());
        flowlogo.add(labLogo);
        flowlogo.setBackground(null);

        // partie Nom d'utilisateur
        labStatutUserName.setForeground(Colors.blue);
        panUserName.add(labUserName);
        panUserName.add(labStatutUserName);
        panUserName.setBackground(null);
        userNamePanel.setBackground(Color.white);
        txtUserName.setPreferredSize(new Dimension(150, 35));
        userNamePanel.setBorder(new FlatLineBorder(new Insets(1, 10, 1, 10), Colors.purple, 2, 20));
        userNamePanel.add(txtUserName);
        txtUserName.setBorder(null);

        //enlève le redmark sur la partie username
        txtUserName.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(labStatutUserName.getText().equals("remplissez")||labStatutUserName.getText().equals("Fenoy")){
                    userNamePanel.setBorder(new FlatLineBorder(new Insets(1, 10, 1, 10), Colors.purple, 2, 20));
                    labStatutUserName.setText("");
                    statut.setText("");
                }
            }
        });

        // partie password
        labStatutTxtPass.setForeground(Color.red);
        panTxtPass.add(labPass);
        panTxtPass.add(labStatutTxtPass);
        panTxtPass.setBackground(null);
       // panTxtPass.setPreferredSize(new Dimension(100, 20));
        imgVisible.setIcon(new ImageIcon(new Sary().Resize("img/Hide.png", 25, 25)));
       
        passPanel.setBorder(new FlatLineBorder(new Insets(1, 10, 1, 10), Colors.purple, 2, 20));
        txtPass.setPreferredSize(new Dimension(150, 25));
        txtPass.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        panEye.add(imgVisible);
        panEye.setBackground(Color.white);

         //enlève le redmark sur la partie password
         txtPass.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(labStatutTxtPass.getText().equals("remplissez")||labStatutTxtPass.getText().equals("Fenoy")){
                    passPanel.setBorder(new FlatLineBorder(new Insets(1, 10, 1, 10), Colors.purple, 2, 20));
                    labStatutTxtPass.setText("");
                    statut.setText("");
                }
            }
        });

       
        panBtnConnect.add(btnConnect);
        panBtnConnect.setBackground(null);

        panPassOublie.add(labForgotPass);
        panPassOublie.setPreferredSize(new Dimension(100,50));
        panPassOublie.setBackground(null);

        panAmbany.add(labPhrase);
        panAmbany.add(labCreer);
        labCreer.setForeground(Colors.blue);
        panAmbany.setBackground(null);
        panSouth.add(panAmbany, BorderLayout.NORTH);
        panStatut.add(statut);
        

        panelInformation.setLayout(new VerticalFlowLayout());
       
        panelInformation.add(panUserName);
        panelInformation.add(userNamePanel);
        panelInformation.add(panTxtPass);
        panelInformation.add(passPanel);
        panelInformation.add(panPassOublie);
        panelInformation.add(panStatut);
        panStatut.setBackground(null);
        statut.setForeground(Color.red);
        panSouth.setBackground(null);
       
        panelInformation.setBackground(Color.white);
        panelInformation.setPreferredSize(new Dimension(230,250));
        panelAdroite.add(panelInformation);

        panelInformation.setBorder(new FlatLineBorder(new Insets(10, 2, 10, 2), Colors.stroke, 1, 20));
        panMiddle.add(flowlogo);
        panMiddle.add(panelAdroite);
        panMiddle.add(panBtnConnect);
        panMiddle.add(panSouth);
       

        slidePan.add(panMiddle);
        slidePan.setBorder(null);
        slidePan.setOpaque(false);

      //  ImageIcon icon = new ImageIcon("img/imageGauche.jpg");
      
        panDivise.add(imageAgauche);
        panDivise.add(slidePan);

        return panDivise;

    }
    
      
       
  
    public Buttons getBtnConnect() {
        return btnConnect;
    }

    public void setBtnConnect(Buttons btnConnect) {
        this.btnConnect = btnConnect;
    }

    public JLabel getStatut() {
        return statut;
    }

    public void setStatut(JLabel statut) {
        this.statut = statut;
    }

    public JLabel getLabStatutUserName() {
        return labStatutUserName;
    }

    public void setLabStatutUserName(JLabel labStatutUserName) {
        this.labStatutUserName = labStatutUserName;
    }

    public JLabel getLabStatutTxtPass() {
        return labStatutTxtPass;
    }

    public void setLabStatutTxtPass(JLabel labStatutTxtPass) {
        this.labStatutTxtPass = labStatutTxtPass;
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(JTextField txtUserName) {
        this.txtUserName = txtUserName;
    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(JPasswordField txtPass) {
        this.txtPass = txtPass;
    }

    public Labels getLabCreer() {
        return labCreer;
    }

    public void setLabCreer(Labels labCreer) {
        this.labCreer = labCreer;
    }
    public JLabel getImgVisible() {
        return imgVisible;
    }

    public void setImgVisible(JLabel imgVisible) {
        this.imgVisible = imgVisible;
    }

    public JPanel getUserNamePanel() {
        return userNamePanel;
    }

    public void setUserNamePanel(JPanel userNamePanel) {
        this.userNamePanel = userNamePanel;
    }

    public JPanel getPassPanel() {
        return passPanel;
    }

    public Labels getLabForgotPass() {
        return labForgotPass;
    }

    public void setLabForgotPass(Labels labForgotPass) {
        this.labForgotPass = labForgotPass;
    }

    public JPanel getPanForgotPass() {
        return panForgotPass;
    }

    public void setPanForgotPass(JPanelSlider panForgotPass) {
        this.panForgotPass = panForgotPass;
    }

    public JPanel getPanelAdroite() {
        return panelAdroite;
    }

    public void setPanelAdroite(JPanel panelAdroite) {
        this.panelAdroite = panelAdroite;
    }

    public JPanel getPanelCreation() {
        return panelCreation;
    }

    public void setPanelCreation(JPanel panelCreation) {
        this.panelCreation = panelCreation;
    }

    public Labels getLabUserName() {
        return labUserName;
    }

    public void setLabUserName(Labels labUserName) {
        this.labUserName = labUserName;
    }

    public Labels getLabPass() {
        return labPass;
    }

    public void setLabPass(Labels labPass) {
        this.labPass = labPass;
    }

    public Labels getLabPhrase() {
        return labPhrase;
    }

    public void setLabPhrase(Labels labPhrase) {
        this.labPhrase = labPhrase;
    }

    public CreationService getCreationService() {
        return creationService;
    }

    public void setCreationService(CreationService creationService) {
        this.creationService = creationService;
    }

    public ForgetPassService getForgetPassService() {
        return forgetPassService;
    }

    public void setForgetPassService(ForgetPassService forgetPassService) {
        this.forgetPassService = forgetPassService;
    }
    
   

   
    


}
