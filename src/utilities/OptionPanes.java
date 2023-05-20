package utilities;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.intellij.openapi.ui.VerticalFlowLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OptionPanes extends JDialog {
    private String message2,message1,icon_path;
    private Labels lab_message1,lab_message2;
    private JButton btn_ok=new JButton("okay");
    private JLabel icon=new JLabel();

    public OptionPanes(JFrame parent, String title,String message1,String message2,String icon_path,boolean modal) {
        super(parent, title, modal);
        // this.setSize(300,200);
       // this.setMinimumSize(new Dimension(300,180));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.message1=message1;
        this.message2=message2;
        this.icon_path=icon_path;

        this.initComponent();
        this.pack();
        this.setVisible(true);

    }
    public void initComponent(){
        JPanel pan=new JPanel();
        pan.setLayout(new BorderLayout());
        pan.setBackground(Color.white);

        //verticalFlowLayout
        JPanel panAgnivo=new JPanel(new VerticalFlowLayout());
        panAgnivo.setOpaque(false);
       
        //label message1
        lab_message1=new Labels(this.message1,Fonts.textFont,Colors.blue,20);

        //label message2
        lab_message2=new Labels(this.message2,Fonts.textFont,Colors.grey,15);

        //icone
        JPanel panIcone=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panIcone.setOpaque(false);
        if(icon_path!=null){
            icon.setIcon(new ImageIcon(new Sary().Resize(this.icon_path,40,40)));
        }
        panIcone.add(this.icon);

        //btn
        JPanel panBtn=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panBtn.add(btn_ok);
        btn_ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OptionPanes.this.dispose();
            }
        });


        //adding
        panAgnivo.add(Box.createVerticalStrut(10));
        panAgnivo.add(lab_message1);
        panAgnivo.add(Box.createVerticalStrut(15));
        panAgnivo.add(lab_message2);

        pan.add(panAgnivo,BorderLayout.CENTER);
        pan.add(panIcone,BorderLayout.WEST);
        pan.add(panBtn,BorderLayout.SOUTH);

        this.add(pan);

    }
    public Labels getLab_message1() {
        return lab_message1;
    }
    public void setLab_message1(Labels lab_message1) {
        this.lab_message1 = lab_message1;
    }
    public Labels getLab_message2() {
        return lab_message2;
    }
    public void setLab_message2(Labels lab_message2) {
        this.lab_message2 = lab_message2;
    }
    public JButton getBtn_ok() {
        return btn_ok;
    }
    public void setBtn_ok(JButton btn_ok) {
        this.btn_ok = btn_ok;
    }
   
    
    
}
