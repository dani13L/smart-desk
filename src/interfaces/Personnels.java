package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.ui.FlatLineBorder;
import utilities.Buttons;
import utilities.Colors;
import utilities.Sary;
import utilities.peronnel.InfoAdditionnel;
import utilities.peronnel.WorkerCard;
import utilities.peronnel.WorkerInfo;
import utilities.peronnel.WorkerRenderer;

public class Personnels extends JPanel {

    private JList<WorkerCard> list;
    private WorkerInfo info;
    private JLabel add;
    private Buttons edit ;
    private List<InfoAdditionnel> infoList = new ArrayList<InfoAdditionnel>();
    private String poste="",cin="",heures="";
    InfoAdditionnel Carte;

    private InfoAdditionnel CIN, Posta;
    
    public Personnels(){
        this.setLayout(new BorderLayout(10,0));
        this.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        this.add(personnelList(),BorderLayout.CENTER);
        this.add(personnelInfo(),BorderLayout.EAST);
    }

    public JPanel personnelList(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new FlatLineBorder(new Insets(0,10,10,10), Colors.stroke,1,20));
        
        JScrollPane pane = new JScrollPane();
        DefaultListModel<WorkerCard> model = new DefaultListModel<>();
        list = new JList<>();

        /// Customization
        list.setBackground(Colors.backgrounds);
        pane.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        pane.setOpaque(false);
        panel.setBackground(Colors.backgrounds);

        /// Setting up list
        list.setModel(model);
        list.setCellRenderer(new WorkerRenderer());
        pane.setViewportView(list);
        panel.add(pane);

        //------------------- Adding new emplyee-------------------
        JPanel addPanel = new JPanel();
        addPanel.setOpaque(false);

        add = new JLabel(new ImageIcon(new Sary().Resize("img/add.png", 45, 45)));
        addPanel.add(add);

        panel.add(addPanel);

        return panel;
    }

    // Right-sided worker information panel
    public JScrollPane personnelInfo(){
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(300,0));
        //panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new FlatLineBorder(new Insets(10,10,10,10), Colors.stroke,1,20));
        panel.setBackground(Colors.backgrounds);

        info = new WorkerInfo("useraa.png", "Employée", "Employée");
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new FlatLineBorder(new Insets(10,10,10,10), Colors.stroke,1,20));


        // ? Ajouter des informations en bas du photo et du nom de la personne

        // * Informations
        CIN = new InfoAdditionnel("CIN",this.cin,false);
        Posta = new InfoAdditionnel("Poste",this.poste,false);
        Carte = new InfoAdditionnel("Carte rfid ", "",true);
        InfoAdditionnel Heure = new InfoAdditionnel("Heure de service",this.heures,true);

        infoList.add(CIN);
        infoList.add(Posta);
        infoList.add(Carte);
        infoList.add(Heure);
         
        
        infoPanel.add(CIN);
        //infoPanel.add(Box.createVerticalStrut(2));
        infoPanel.add(Posta);
        //infoPanel.add(Box.createVerticalStrut(2));
        infoPanel.add(Heure);
        //infoPanel.add(Box.createVerticalStrut(2));
        infoPanel.add(Carte);
        Carte.setVisible(false);

        // * Panel pour le bouton de validation de modification
 
        JPanel editPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        editPanel.setOpaque(false);
        edit = new Buttons("Modifier");
        edit.setBackground(Colors.blue);

        editPanel.add(edit);
        edit.setVisible(false);

        panel.add(info);
        panel.add(Box.createVerticalStrut(10));
        panel.add(infoPanel);
        panel.add(editPanel);

        scrollPane.getViewport().add(panel);
        return scrollPane;
    }

    // Set the info below editable
    public void setEditable(Boolean editable){
        if(editable){
            for(InfoAdditionnel info: infoList){
                info.setEditable(true);
            }
            Carte.setVisible(true);
        }else{
            for(InfoAdditionnel info: infoList){
                info.setEditable(false);
            }
            Carte.setVisible(false);
        }
    }
    
    public List<InfoAdditionnel> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<InfoAdditionnel> infoList) {
        this.infoList = infoList;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getHeures() {
        return heures;
    }

    public void setHeures(String heures) {
        this.heures = heures;
    }

    public JList<WorkerCard> getList() {
        return list;
    }

    public void setList(JList<WorkerCard> list) {
        this.list = list;
    }

    public WorkerInfo getWorkerInfo() {
        return info;
    }

    public void setWorkerInfo(WorkerInfo info) {
        this.info = info;
    }

    public JLabel getAdd() {
        return add;
    }

    public void setAdd(JLabel add) {
        this.add = add;
    }

    public Buttons getEdit() {
        return edit;
    }

    public void setEdit(Buttons edit) {
        this.edit = edit;
    }

    public InfoAdditionnel getCarte() {
        return Carte;
    }

    public void setCarte(InfoAdditionnel carte) {
        Carte = carte;
    }

    public InfoAdditionnel getCIN() {
        return CIN;
    }

    public void setCIN(InfoAdditionnel cIN) {
        CIN = cIN;
    }

    public void setPoste(InfoAdditionnel poste) {
        Posta = poste;
    }
    public InfoAdditionnel getPosta(){
        return Posta;
    }
    
    
}
