package utilities.peronnel;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class WorkerRenderer implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        WorkerCard card = (WorkerCard) value;
        card.setActive(isSelected?true:false);
        return card;
    }
    
}
