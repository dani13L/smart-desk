package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import diu.swe.habib.JPanelSlider.JPanelSlider;
import utilities.Buttons;
import utilities.Colors;
import utilities.Labels;
import utilities.Sary;


public class ForgetPass extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel panTitre=new JPanel(new FlowLayout(FlowLayout.CENTER));
	private Labels labTitre=new Labels("Mot de pass oublié","Arial",Colors.blue,25);
	private Labels fanazavana=new Labels("<html>Entrez votre nom et votre prenom  <br>pour verifier et renitialisr votre compte<html>","Arial",Colors.grey,15);
	
	private Labels labNom=new Labels("Nom","Arial",Colors.text,13);
	private JTextField txtForgotNom=new JTextField();
	
	private Labels labPrenom=new Labels("Prenom","Arial",Colors.text,13);
	private JTextField txtForgotPrenom=new JTextField();
	
	private Buttons next=new Buttons("Suivant");
	private Labels retour= new Labels("Rétour","Arial",Colors.blue,15);
	
	private JPanel panBackLogin=new JPanel(new FlowLayout(FlowLayout.CENTER));
	private Labels backLogForgot=new Labels("LogIn","Arial",Colors.blue,15);
	private Labels labQuestionForgetPass=new Labels("","Arial",Colors.text,15);
	private JTextField txtReponseForgot=new JTextField();
	
	private JPasswordField txtNewPass1=new JPasswordField();
	private JPasswordField txtNewPass2=new JPasswordField();
	
	private JPanel panNomForgot=new JPanel(new BorderLayout());
	private JPanel panPrenomForgot=new JPanel(new BorderLayout());
	private JLabel statuPassForgot1=new JLabel("");
	private JLabel statuPassForgot2=new JLabel("");

	private JLabel saryMaso=new JLabel(new ImageIcon(new Sary().Resize("img/Hide.png", 25, 25)));

	private JPanel panAutentification=new JPanel();
	private JPanel panQuestion=new JPanel();
	private JPanel panRecoveryPass=new JPanel();

	private JPanelSlider slider=new JPanelSlider();

	private Labels LabFanazavana=new Labels("<html>Tsy maintsy ampidirinao eto <br>le valiny nataonao tamin nanao creation compte iny"
				+ "<br>mba ahafana mamantatra hoe anao le compte!!!</html>","Arial",Colors.grey,15);

	//statut pour chaque panel
	private Labels statutAutentification=new Labels("","Arial",Colors.blue,15);
	private Labels statutQuestion=new Labels("","Arial",Colors.blue,15);
	private Labels statutRecovery=new Labels("","Arial",Colors.blue,15);
	
	public ForgetPass(){
		//partie NORTH
		setLayout(new VerticalFlowLayout());
		panTitre.add(labTitre);
		add(panTitre);
		
		panAutentification=autentification();
		panQuestion=forgotPanQuestion();
		panRecoveryPass=forgotPanPassRecover();

		//partie CENTER
		slider.setOpaque(false);
		slider.setBorder(null);
		slider.add(panAutentification);
		slider.add(panQuestion);
		slider.add(panRecoveryPass);
		add(slider);

		//patie sud 
		JPanel panNext=new JPanel(new FlowLayout());
        next.setPreferredSize(new Dimension(100,40));
        panNext.add(next);
        add(panNext);
        JPanel panBtn = new JPanel(new FlowLayout( FlowLayout.LEFT));
        panBtn.add(retour);
        add(panBtn);

		
	}
	public JPanel autentification() {
		JPanel pan=new JPanel();
		pan.setLayout(new VerticalFlowLayout(VerticalFlowLayout. MIDDLE,10,10,true,false));
	
		fanazavana.setForeground(Color.gray);
		
		backLogForgot.setForeground(Colors.blue);
		panBackLogin.add(backLogForgot);
		panBackLogin.setBackground(null);
		
		txtForgotNom.setPreferredSize(new Dimension(100,40));
		panNomForgot.add(labNom,BorderLayout.WEST);
		panNomForgot.setBackground(null);
		 
		txtForgotPrenom.setPreferredSize(new Dimension(100,40));
		panPrenomForgot.add(labPrenom,BorderLayout.WEST);
		panPrenomForgot.setBackground(null);

		txtForgotNom.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));
		txtForgotPrenom.setBorder(new FlatLineBorder(new Insets(2, 10, 2, 10), Colors.purple, 3, 20));

		pan.add(fanazavana);
		pan.add(panNomForgot);
		pan.add(txtForgotNom);
		pan.add(panPrenomForgot);
		pan.add(txtForgotPrenom);
		pan.add(statutAutentification);
		pan.setBackground(Color.white);

		JPanel panOutPan= new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan.setPreferredSize(new Dimension(280,250));
		pan.setBorder(new FlatLineBorder(new Insets(10, 2, 10, 2), Colors.stroke, 1, 20));
		panOutPan.add(pan);

		return panOutPan;
	}
	
	//panel Question
	public JPanel forgotPanQuestion() {
		JPanel pan=new JPanel(new VerticalFlowLayout(VerticalFlowLayout. MIDDLE,10,10,true,false));
				
		txtReponseForgot.setBorder(new FlatLineBorder(new Insets(12, 10, 12, 10), Colors.purple, 3, 20));
		
		pan.add(LabFanazavana);
		pan.add(labQuestionForgetPass);
		pan.add(txtReponseForgot);
		pan.add(statutQuestion);
		pan.setBackground(Color.white);
		
		pan.setPreferredSize(new Dimension(280,250));
		pan.setBorder(new FlatLineBorder(new Insets(10, 2, 10, 2), Colors.stroke, 1, 20));
		JPanel panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(pan);

		return panel;
	}
	
	//panel renitialisation password
		public  JPanel forgotPanPassRecover() {
			JPanel pan=new JPanel(new VerticalFlowLayout(VerticalFlowLayout. MIDDLE,10,10,true,false));
			Labels forgotPass1= new Labels("Nouveau mot de pass","Arial",Colors.text,13);
			Labels forgotPass2=new Labels("Retapez le nouveau de mot de pass","Arial",Colors.text,13);
			JPanel panValider=new JPanel(new BorderLayout());
			JPanel panPass1Forget=new JPanel(new BorderLayout());
			JPanel panPass2Forget=new JPanel(new BorderLayout());
			
			panPass1Forget.add(forgotPass1,BorderLayout.WEST);
			panPass1Forget.add(statuPassForgot1,BorderLayout.CENTER);
			panPass1Forget.setBackground(null);
			panPass2Forget.add(forgotPass2,BorderLayout.WEST);
			panPass2Forget.add(statuPassForgot2,BorderLayout.CENTER);
			panPass2Forget.setBackground(null);
			panPass2Forget.add(saryMaso,BorderLayout.EAST);
			
			txtNewPass1.setBorder(new FlatLineBorder(new Insets(12, 10, 12, 10), Colors.purple, 3, 20));
			txtNewPass2.setBorder(new FlatLineBorder(new Insets(12, 10, 12, 10), Colors.purple, 3, 20));
			
			pan.add(panPass1Forget);
			pan.add(txtNewPass1);
			pan.add(panPass2Forget);
			pan.add(txtNewPass2);
			pan.add(statutRecovery);
			pan.add(panValider);
			
			pan.setBackground(Color.white);
			JPanel panel=new JPanel(new FlowLayout());
			panel.add(pan);
			pan.setPreferredSize(new Dimension(280,250));
			
			return panel;
		}
		public Buttons getNextForgot1() {
			return next;
		}
		public void setNextForgot1(Buttons nextForgot1) {
			this.next = nextForgot1;
		}
		
		public Labels getBackForgot1() {
			return retour;
		}
		public void setBackForgot1(Labels backForgot1) {
			this.retour = backForgot1;
		}
		public JPanel getPanAutentification() {
			return panAutentification;
		}
		public void setPanAutentification(JPanel panAutentification) {
			this.panAutentification = panAutentification;
		}
		public JPanel getPanQuestion() {
			return panQuestion;
		}
		public void setPanQuestion(JPanel panQuestion) {
			this.panQuestion = panQuestion;
		}
		public JPanel getPanRecoveryPass() {
			return panRecoveryPass;
		}
		public void setPanRecoveryPass(JPanel panRecoveryPass) {
			this.panRecoveryPass = panRecoveryPass;
		}
		public JTextField getTxtForgotNom() {
			return txtForgotNom;
		}
		public void setTxtForgotNom(JTextField txtForgotNom) {
			this.txtForgotNom = txtForgotNom;
		}
		public JTextField getTxtForgotPrenom() {
			return txtForgotPrenom;
		}
		public void setTxtForgotPrenom(JTextField txtForgotPrenom) {
			this.txtForgotPrenom = txtForgotPrenom;
		}
		public Labels getLabQuestionForgetPass() {
			return labQuestionForgetPass;
		}
		public void setLabQuestionForgetPass(Labels labQuestionForgetPass) {
			this.labQuestionForgetPass = labQuestionForgetPass;
		}
		public JTextField getTxtReponseForgot() {
			return txtReponseForgot;
		}
		public void setTxtReponseForgot(JTextField txtReponseForgot) {
			this.txtReponseForgot = txtReponseForgot;
		}
		
		public JLabel getSaryMaso() {
			return saryMaso;
		}
		public void setSaryMaso(JLabel saryMaso) {
			this.saryMaso = saryMaso;
		}
		public Labels getStatutAutentification() {
			return statutAutentification;
		}
		public void setStatutAutentification(Labels statutAutentification) {
			this.statutAutentification = statutAutentification;
		}
		public Labels getStatutQuestion() {
			return statutQuestion;
		}
		public void setStatutQuestion(Labels statutQuestion) {
			this.statutQuestion = statutQuestion;
		}
		public Labels getStatutRecovery() {
			return statutRecovery;
		}
		public void setStatutRecovery(Labels statutRecovery) {
			this.statutRecovery = statutRecovery;
		}
		public JPasswordField getTxtNewPass1() {
			return txtNewPass1;
		}
		public void setTxtNewPass1(JPasswordField txtNewPass1) {
			this.txtNewPass1 = txtNewPass1;
		}
		public JPasswordField getTxtNewPass2() {
			return txtNewPass2;
		}
		public void setTxtNewPass2(JPasswordField txtNewPass2) {
			this.txtNewPass2 = txtNewPass2;
		}
		public Labels getBackLogForgot() {
			return backLogForgot;
		}
		public void setBackLogForgot(Labels backLogForgot) {
			this.backLogForgot = backLogForgot;
		}
		public JPanelSlider getSlider() {
			return slider;
		}
		public void setSlider(JPanelSlider slider) {
			this.slider = slider;
		}
		public JLabel getStatuPassForgot1() {
			return statuPassForgot1;
		}
		public void setStatuPassForgot1(JLabel statuPassForgot1) {
			this.statuPassForgot1 = statuPassForgot1;
		}
		public JLabel getStatuPassForgot2() {
			return statuPassForgot2;
		}
		public void setStatuPassForgot2(JLabel statuPassForgot2) {
			this.statuPassForgot2 = statuPassForgot2;
		}
		public Labels getLabTitre() {
			return labTitre;
		}
		public void setLabTitre(Labels labTitre) {
			this.labTitre = labTitre;
		}
		public Labels getFanazavana() {
			return fanazavana;
		}
		public void setFanazavana(Labels fanazavana) {
			this.fanazavana = fanazavana;
		}
		public Labels getLabNom() {
			return labNom;
		}
		public void setLabNom(Labels labNom) {
			this.labNom = labNom;
		}
		public Labels getLabPrenom() {
			return labPrenom;
		}
		public void setLabPrenom(Labels labPrenom) {
			this.labPrenom = labPrenom;
		}
		public Buttons getNext() {
			return next;
		}
		public void setNext(Buttons next) {
			this.next = next;
		}
		public Labels getRetour() {
			return retour;
		}
		public void setRetour(Labels retour) {
			this.retour = retour;
		}
		public Labels getLabFanazavana() {
			return LabFanazavana;
		}
		public void setLabFanazavana(Labels labFanazavana) {
			LabFanazavana = labFanazavana;
		}
		
		
		

}
