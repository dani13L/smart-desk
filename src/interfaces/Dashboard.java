package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.auth.LoginService;

import com.formdev.flatlaf.ui.FlatLineBorder;

import services.AproposService;
import services.ClockInService;
import services.ClockOutService;
import services.HistoricService;
import services.OverviewService;
import services.ParametreService;
import services.PersonneService;
import services.WebSocketService;
import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;
import utilities.menu.UserProfile;
public class Dashboard extends JPanel {

    Color backgroundColor = new Color(248, 249, 250);
    Color textColor = new Color(52, 58, 64);
    
    private PersonneService personneService = new PersonneService();
    private HistoricService historicService = new HistoricService();
    private ClockInService clockInService = new ClockInService();
    private ClockOutService clockOutService = new ClockOutService();
    private ParametreService parametreService = new ParametreService();
    private OverviewService overviewService = new OverviewService();
    private WebSocketService webService ;


    private Labels welcomeLabel;
    private JPanel placeholder;
    public UserProfile profile = new UserProfile("user", "Username", "Occupation");

    public Dashboard(){

        placeholder = historicService.getHistorics();
        webService = new WebSocketService(historicService.getHistorics().getScanner().getToggle());

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        this.add(welcomeBar(),BorderLayout.NORTH);
        this.add(placeholder,BorderLayout.CENTER);

        
        //this.add(personneService.getPersonnels(),BorderLayout.CENTER);
    }

    // * fonction qui crée l'entête 
    public JPanel welcomeBar(){

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setBorder(new FlatLineBorder(new Insets(0,10,0,10), Colors.stroke,1,20));

        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        welcomePanel.setOpaque(false);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        searchPanel.setOpaque(false);

        welcomeLabel = new Labels("Bienvenue",Fonts.textFont, Colors.text, 30);
     

        welcomePanel.add(welcomeLabel);

        JButton search = new JButton("Rechercher");
        search.setPreferredSize(new Dimension(100,30));
        search.setBackground(new Color(138, 60, 183));
        search.setForeground(Color.white);
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150,30));

        searchPanel.add(profile);//searchPanel.add(search);

        panel.add(welcomePanel);panel.add(searchPanel);

        return panel;
    }

    // * fonction qui charge les élements du tableau de bord
    public void load(JPanel replacement){
        this.remove(placeholder);
        placeholder = replacement;
        this.add(placeholder,BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }


    // * Getter and Setter 
    public PersonneService getPersonneService() {
        return personneService;
    }

    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    public HistoricService getHistoricService() {
        return historicService;
    }

    public void setHistoricService(HistoricService historicService) {
        this.historicService = historicService;
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public void setWelcomeLabel(Labels welcomeLabel) {
        this.welcomeLabel = welcomeLabel;
    }

    public ClockInService getClockInService() {
        return clockInService;
    }

    public void setClockInService(ClockInService clockInService) {
        this.clockInService = clockInService;
    }

    public ClockOutService getClockOutService() {
        return clockOutService;
    }

    public void setClockOutService(ClockOutService clockOutService) {
        this.clockOutService = clockOutService;
    }

    public ParametreService getParametreService() {
        return parametreService;
    }

    public void setParametreService(ParametreService parametreService) {
        this.parametreService = parametreService;
    }

    public OverviewService getOverviewService() {
        return overviewService;
    }

    public void setOverviewService(OverviewService overviewService) {
        this.overviewService = overviewService;
    }

    
    
    
    
}
