package utilities.ajouter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import com.formdev.flatlaf.ui.FlatEmptyBorder;

public class ComboEditor extends BasicComboBoxEditor{

	private JLabel label = new JLabel();
	private JPanel panel = new JPanel();
	
	public ComboEditor() {
		label.setFont(new Font("Century Gothic",Font.PLAIN,16));
		label.setForeground(Color.DARK_GRAY);
		
		panel.add(label);
		panel.setBorder(new FlatEmptyBorder(new Insets(0,0,10,0)));
	}
	
	@Override
	public Component getEditorComponent() {

		return this.panel;
	}
	
	@Override
	public Object getItem() {
		
		return label.getText();
	}
	@Override
	public void setItem(Object arg0) {
		
		label.setText(arg0==null?null:arg0.toString());
	}
}
