package utilities.menu;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class Cell extends JPanel implements ListCellRenderer<Object> {

    public Cell(){
        setBackground(Color.blue);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        MenuItems panel = (MenuItems) value;
        panel.setActive(isSelected? true:false);
        //panel.setForeground(isSelected? Color.WHITE:new Color(138, 60, 183));
        return panel;
    }
    
}
