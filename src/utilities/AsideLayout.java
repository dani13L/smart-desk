package utilities;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class AsideLayout extends JPanel {

    public AsideLayout(JComponent left,JComponent right){
        JPanel leftside = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        leftside.setOpaque(false);
        rightSide.setOpaque(false);

        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.setOpaque(false);

        leftside.add(left);
        rightSide.add(right);

        this.add(leftside);
        this.add(rightSide);
    }
    
}
