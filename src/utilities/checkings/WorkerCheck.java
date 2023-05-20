package utilities.checkings;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.Colors;
import utilities.Fonts;
import utilities.ImageProfile;
import utilities.Labels;

public class WorkerCheck extends JPanel{
    
    private String icon,name,work_time,clock_in;
    Labels nam, schedule;

    public WorkerCheck(String icon, String name, String work_time, String clock_in) {
        this.icon = icon;
        this.name = name;
        this.work_time = work_time;
        this.clock_in = clock_in;

        this.setBackground(Colors.backgrounds);
        this.setBorder(new FlatLineBorder(new Insets(5,0,0,0), getBackground(), 0, 20));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(profile());this.add(timeCheckedIn());
    }

    //Left-sided worker profile
    private JPanel profile(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel icon = new JLabel(new ImageIcon(new ImageProfile().ResizeCercle("img/workers/"+this.icon, 50, 50, Colors.purple)));
        
        JPanel namePanel = new JPanel(new VerticalFlowLayout());
         nam  = new Labels(this.name,Fonts.textFont,Colors.text,15);
         schedule = new Labels("prévu: "+this.work_time,Fonts.textFont,Color.gray,11);

        //Customization
        panel.setOpaque(false);namePanel.setOpaque(false);

        //Laying out elements
        namePanel.add(nam);namePanel.add(schedule);

        panel.add(icon);
        panel.add(namePanel);

        return panel;

    }

    //Clock in panel
    private JPanel timeCheckedIn(){

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel timeLabel = new JLabel(this.clock_in);

        //Customization
        timeLabel.setFont(new Font(Fonts.textFont,Font.BOLD,15));
        timeLabel.setForeground(Color.gray);
        panel.setOpaque(false);

        //Putting in place
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(timeLabel);

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

    public Labels getNam() {
        return nam;
    }

    public void setNam(Labels nam) {
        this.nam = nam;
    }

    public Labels getSchedule() {
        return schedule;
    }

    public void setSchedule(Labels schedule) {
        this.schedule = schedule;
    }
    
}
