package utilities;

import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;

import java.awt.*;

public class Buttons extends JPanel {

    private String text;
    private boolean isOutlined;
    private Color background = Colors.purple;
    private Color stroke = Colors.purple;
    private Labels textLabel;

    public Buttons(String text){
        this.text = text;   
        UI();
    }

    private void UI(){

        this.setLayout(new FlowLayout());
        this.textLabel = new Labels(this.text, Fonts.textFont, Colors.backgrounds, 15);
        this.setBackground(background);
        this.setBorder(new FlatLineBorder(new Insets(6,10,3,10), getBackground(),0,20));
        this.add(textLabel);

    }


    public void isOutlined(boolean outlined){
        if(outlined){
            this.textLabel.setForeground(Colors.purple);
            this.setBackground(Colors.backgrounds);
            this.setBorder(new FlatLineBorder(new Insets(6,10,3,10), this.stroke,0,20));
            //this.add(textLabel);
        }
    }


    //Getters and Setters
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Color getBackground() {
        return background;
    }
    public void setBackground(Color background) {
        this.background = background;
    }
    public Color getStroke() {
        return stroke;
    }
    public void setStroke(Color stroke) {
        this.stroke = stroke;
    }

    public Labels getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(Labels textLabel) {
        this.textLabel = textLabel;
    }

    
}
