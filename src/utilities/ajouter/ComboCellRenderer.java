package utilities.ajouter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ComboCellRenderer extends DefaultListCellRenderer{
	
	public ComboCellRenderer() {}
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {

		JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
		
		
		setFont(new Font("Century Gothic",Font.PLAIN,16));
		setForeground(Color.DARK_GRAY);
		label.setText(value.toString());
		
		if(isSelected){
			setForeground(Color.WHITE);
		}else {
			setForeground(Color.DARK_GRAY);
		}

		
		return label;
	}
}
