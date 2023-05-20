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

import services.AccountService;
import services.ApparenceService;
import services.AproposService;
import services.ExporterService;
import services.ScannerRFIDService;
import services.Term_conditionService;
import utilities.Colors;
import utilities.menu.Cell;
import utilities.menu.MenuItems;

public class Parametre extends JPanel {

    private JList<MenuItems> list;
    private JPanel placeholder,thirdParty;

    private AccountService accountService = new AccountService();
    private ExporterService exporterService= new ExporterService();
    private ApparenceService apparenceService = new ApparenceService();
    private ScannerRFIDService scannerRFIDService= new ScannerRFIDService();
    private AproposService aproposService=new AproposService();
    private Term_conditionService term_conditionService=new Term_conditionService();
    
    public MenuItems menuApparence=new MenuItems("login", "Apparence");
    public MenuItems menuScan=new MenuItems("logout", "Scanner RFID");
    public MenuItems menuCompte=new MenuItems("writing", "Compte");
    public MenuItems menuExporter=new MenuItems("clipboard", "Exporter");
    public MenuItems menuApropo=new MenuItems("disposition", "A propos");
    public MenuItems menuTerm_cond=new MenuItems("login", "Term & condition");


    public static String txtApparence,txtScanner,txtCompte,txtExport,txtAbout,txtTerm;

    public Parametre(){

        this.thirdParty  = apparenceService.getApparence();

        this.setLayout(new BorderLayout(10,0));
        this.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        this.add(options(),BorderLayout.WEST);
        this.add(optionAction(),BorderLayout.CENTER);
    }
    private JPanel options(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new FlatLineBorder(new Insets(0,10,10,10), Colors.stroke,1,20));
        panel.setBackground(Colors.backgrounds);

        list = new JList<>();
        DefaultListModel<MenuItems> model = new DefaultListModel<>();
        JScrollPane pane = new JScrollPane();

        list.setModel(model);
        list.setCellRenderer(new Cell());
        pane.setViewportView(list);

        model.addElement(menuApparence);
        model.addElement(menuScan);
        model.addElement(menuCompte);
        model.addElement(menuExporter);
        model.addElement(menuApropo);
        model.addElement(menuTerm_cond);

        pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        pane.setBackground(Colors.backgrounds);
        list.setBackground(Colors.backgrounds);

        panel.add(pane);

        return panel;
    }

    private JPanel optionAction(){
        placeholder = new JPanel();
        placeholder.setLayout(new BoxLayout(placeholder,BoxLayout.Y_AXIS));
        placeholder.setBorder(new FlatLineBorder(new Insets(0,10,10,10), Colors.stroke,1,20));
        placeholder.setBackground(Colors.backgrounds);

        placeholder.add(thirdParty);
        return placeholder;
    }               

    //Method to load setting content 
    public void load(JPanel panely){
        placeholder.remove(thirdParty);
        thirdParty = panely;
        placeholder.add(thirdParty);
        placeholder.revalidate();
        placeholder.repaint();
    }

    public JList<MenuItems> getList() {
        return list;
    }
    public void setList(JList<MenuItems> list) {
        this.list = list;
    }
   

    //getter and setter accountService
    public AccountService getAccountService() {
        return accountService;
    }
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    public ExporterService getExporterService() {
        return exporterService;
    }
    public void setExporterService(ExporterService exporterService) {
        this.exporterService = exporterService;
    }
    public ApparenceService getApparenceService() {
        return apparenceService;
    }
    public void setApparenceService(ApparenceService apparenceService) {
        this.apparenceService = apparenceService;
    }
    public ScannerRFIDService getScannerRFIDService() {
        return scannerRFIDService;
    }
    public void setScannerRFIDService(ScannerRFIDService scannerRFIDService) {
        this.scannerRFIDService = scannerRFIDService;
    }
    public AproposService getAproposService() {
        return aproposService;
    }
    public void setAproposService(AproposService aproposService) {
        this.aproposService = aproposService;
    }
    public Term_conditionService getTerm_conditionService() {
        return term_conditionService;
    }
    public void setTerm_conditionService(Term_conditionService term_conditionService) {
        this.term_conditionService = term_conditionService;
    }
    
    
    
}
