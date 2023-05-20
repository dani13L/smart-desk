package utilities.historic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.Colors;
import utilities.Fonts;
import utilities.ImageProfile;

public class Historic extends JPanel{
    
    private String icon,name,work_time,clock_in,state;

    public Historic(String icon, String name, String work_time, String clock_in, String state) {
        this.icon = icon;
        this.name = name;
        this.work_time = work_time;
        this.clock_in = clock_in;
        this.state = state;

        this.setBackground(Colors.backgrounds);
        this.setBorder(new FlatLineBorder(new Insets(5,0,0,0), getBackground(), 0, 20));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(profile());this.add(clockIn());
        
    }

    private JPanel profile(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel icon = new JLabel(new ImageIcon(new ImageProfile().ResizeCercle("img/workers/"+this.icon, 50, 50, Colors.purple)));
        
        JPanel namePanel = new JPanel(new VerticalFlowLayout());
        JLabel name  = new JLabel(this.name);
        JLabel schedule = new JLabel("prévu: "+this.work_time);

        //Customization
        schedule.setFont(new Font(Fonts.textFont,Font.BOLD,11));
        schedule.setForeground(Color.gray);
        name.setFont(new Font(Fonts.textFont,Font.BOLD,15));
        name.setForeground(Colors.text);
        panel.setOpaque(false);namePanel.setOpaque(false);

        //Laying out elements
        namePanel.add(name);namePanel.add(schedule);

        panel.add(icon);
        panel.add(namePanel);

        return panel;

    }

    //Clock in panel
    private JPanel clockIn(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel timeLabel = new JLabel(this.clock_in);
        JPanel statuPanel = new JPanel();
        JLabel statuLabel = new JLabel(this.state);

        //Customization
        timeLabel.setFont(new Font(Fonts.textFont,Font.BOLD,15));
        timeLabel.setForeground(Color.gray);
        statuPanel.setBorder(new FlatLineBorder(new Insets(2,5,2,5), Colors.purple,1,20));
        statuPanel.setBackground(Colors.purple);
        statuLabel.setFont(new Font(Fonts.textFont,Font.BOLD,15));
        statuLabel.setForeground(Color.white);
        panel.setOpaque(false);

        //Putting in place
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        statuPanel.add(statuLabel);
        panel.add(timeLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(statuPanel);

        return panel;

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public String getClock_in() {
        return clock_in;
    }

    public void setClock_in(String clock_in) {
        this.clock_in = clock_in;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    
    
}
