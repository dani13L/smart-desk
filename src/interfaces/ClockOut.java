package interfaces;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.ui.FlatLineBorder;

import utilities.Colors;
import utilities.checkings.Overview;
import utilities.checkings.WorkerCheck;
import utilities.historic.HistoricRenderer;

public class ClockOut extends JPanel{

    private JList<WorkerCheck> list;
    public static Overview overview = new Overview();

    public ClockOut(){
        this.setLayout(new BorderLayout(10,0));
        this.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        this.add(poitagePanel(),BorderLayout.CENTER);
        this.add(ClockOut.overview.overviewInfo(),BorderLayout.EAST);
    }
    private JPanel poitagePanel(){

        JPanel panel = new JPanel();
        panel.setBackground(Colors.backgrounds);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new FlatLineBorder(new Insets(0,10,10,10), Colors.stroke,1,20));
        
        JScrollPane pane = new JScrollPane();
        DefaultListModel<WorkerCheck> model = new DefaultListModel<>();
        list = new JList<>();

        list.setBackground(Colors.backgrounds);
        pane.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        pane.setOpaque(false);

        list.setModel(model);
        list.setCellRenderer(new HistoricRenderer());
        pane.setViewportView(list);

        panel.add(pane);
        return panel;
    }
    public JList<WorkerCheck> getList() {
        return list;
    }
    public void setList(JList<WorkerCheck> list) {
        this.list = list;
    }

    
    
}
