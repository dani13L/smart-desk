package utilities;

import javax.swing.JLabel;
import java.awt.*;

public class Labels extends JLabel {

    private String font;
    private int size;
    private Color textColor;
    private String textFace;

    public Labels(String text,String font,Color color, int size){

        this.textFace = text;
        this.font  = font;
        this.size = size;
        this.textColor = color;

        setText(this.textFace);
        setFont(new Font(this.font,Font.BOLD,this.size));
        setForeground(this.textColor);
    }

    public void isLight(boolean islight){
        if(islight){
            setFont(new Font(this.font,Font.PLAIN,this.size));
        }
    }

    //changer taille police
    public void changeFont(int pourcent){
        //int newSize=(this.size*pourcent)/50;
        int newSize=this.size;
        switch (pourcent) {
            case 15:
                newSize -= 5;
                break;
            case 50:
                newSize = this.size;
                break;
            case 75:
                newSize += 10;
                break;

        }
        this.setFont(new Font(this.font,Font.BOLD,newSize));
    }

    //Getters and Setters
    public String getFontString() {
        return font;
    }

    public void setFontString(String font) {
        this.font = font;
    }

    public int getSizeString() {
        return size;
    }

    public void setSizeString(int size) {
        this.size = size;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    
    
}
