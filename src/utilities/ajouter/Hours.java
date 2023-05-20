package utilities.ajouter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utilities.Sary;

public class Hours extends JPanel{
	
	private JButton bouton = new JButton();
	private JTextField hourPicker = new JTextField();
	
	public Hours() {
		
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		bouton.setIcon(new ImageIcon(new Sary().Resize("img/Horloge.png",18,18)));
		hourPicker.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0,new Color(0,0,0,60)));
		//bouton.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1,new Color(0,0,0,60)));
		
		
		bouton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//timePicker.showPopup(Hours.this,-100,-200);
				new ShowTimePicker(Hours.this).setVisible(true);
				
				
			}
		});
		
		add(hourPicker);
		add(bouton);
		
	}

	public JTextField getHourPicker() {
		return hourPicker;
	}

	public void setHourPicker(JTextField hourPicker) {
		this.hourPicker = hourPicker;
	}

	public JButton getBouton() {
		return bouton;
	}

	public void setBouton(JButton bouton) {
		this.bouton = bouton;
	}
	
	
}
