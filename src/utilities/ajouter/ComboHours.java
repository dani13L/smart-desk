package utilities.ajouter;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ComboHours extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5266447708973053123L;

	private Hours hours = new Hours();
	
	public ComboHours() {
		
		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(hours);
		
	}

	public Hours getHours() {
		return hours;
	}

	public void setHours(Hours hours) {
		this.hours = hours;
	}
	

}
