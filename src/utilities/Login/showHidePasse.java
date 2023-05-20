package utilities.Login;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import utilities.Sary;

public class showHidePasse {
	
	
	//methode visible mot de pass
	public static void showPass(JPasswordField pass1,JLabel icone) {
				
		icone.setIcon(new ImageIcon(new Sary().Resize("img/Hide.png", 25, 25)));
				pass1.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
					
					}
//methode hide mot de pass
	public static void hidePass(JPasswordField pass1,JLabel icone) {
		 icone.setIcon(new ImageIcon(new Sary().Resize("img/showPass.png", 25, 25)));
		pass1.setEchoChar('\u0000');
	}

}
