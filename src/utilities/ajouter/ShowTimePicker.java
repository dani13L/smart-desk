package utilities.ajouter;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

public class ShowTimePicker extends JDialog{
	
	private String result;
	private TimePickerByMasi tpm = new TimePickerByMasi();
	
	public ShowTimePicker(Hours hours) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550,330);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		tpm.getChoisir().addMouseListener(new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent arg0) {

				super.mousePressed(arg0);
				result = tpm.getcHeure().getSelectedItem().toString()+":"+tpm.getcMinute().getSelectedItem().toString()+":"+tpm.getcSeconde().getSelectedItem().toString();
				hours.getHourPicker().setText(result);
				dispose();
			}
		});
		
		tpm.getAnnuler().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				super.mousePressed(arg0);
				dispose();
			}
		});
		
		add(tpm,BorderLayout.CENTER);
		
		
		
	}

}
