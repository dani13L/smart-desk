 package interfaces;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.Sary;
import utilities.menu.Cell;
import utilities.menu.MenuItems;

public class Menus extends JPanel {

    public MenuItems dash,debut,fin,list,setting;
    
    public void setDash(MenuItems dash) {
        this.dash = dash;
    }

    public void setDebut(MenuItems debut) {
        this.debut = debut;
    }

    public void setFin(MenuItems fin) {
        this.fin = fin;
    }

    public void setList(MenuItems list) {
        this.list = list;
    }

    public MenuItems getDash() {
        return dash;
    }

    public MenuItems getDebut() {
        return debut;
    }

    public MenuItems getFin() {
        return fin;
    }

    public MenuItems getList() {
        return list;
    }

    JList<MenuItems> menuList;

    public Menus(){
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //List of menu
        menuList = new JList<>();
        DefaultListModel<MenuItems> model = new DefaultListModel<>();
        JScrollPane pane = new JScrollPane();
        
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
        JPanel bottom = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.BOTTOM));
        
        //Customization
        top.setBackground(new Color(248, 249, 250));bottom.setBackground(new Color(248, 249, 250));
        pane.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        pane.setOpaque(false);
        menuList.setBackground(new Color(248, 249, 250));

        //Menu instances
        dash = new MenuItems("disposition", "Tableau de bord");
        debut = new MenuItems("login", "Début de service");
        fin = new MenuItems("logout", "Fin de service");
        list = new MenuItems("clipboard", "Personnels");
        setting = new MenuItems("settings", "Paramètres");

        //Smart teknolojia logo for bottom panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        logoPanel.setOpaque(false);
        //logoPanel.setPreferredSize(new Dimension(0,50));
        JLabel logo = new JLabel(new ImageIcon(new Sary().Resize("img/santa.png", 100, 85)));     

        //Setting up the list of menu
        model.addElement(dash);model.addElement(debut);model.addElement(fin);model.addElement(list);
        menuList.setCellRenderer(new Cell());
        menuList.setModel(model);
        menuList.setSelectedIndex(0);
        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setting.setActive(false);
            }
        });

        pane.setViewportView(menuList);

        logoPanel.add(logo);
        top.add(logoPanel);top.add(pane);
        
        //bottom.add(logo);bottom.add(Box.createVerticalStrut(50));
        bottom.add(setting);  
        this.add(top);this.add(bottom);
    }

    public JList<MenuItems> getMenuList() {
        return menuList;
    }

    public void setMenuList(JList<MenuItems> menuList) {
        this.menuList = menuList;
    }

    public MenuItems getSetting() {
        return setting;
    }

    public void setSetting(MenuItems setting) {
        this.setting = setting;
    }

    
    
}
