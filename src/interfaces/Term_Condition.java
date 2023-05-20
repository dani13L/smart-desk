package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import com.intellij.openapi.ui.VerticalFlowLayout;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;
public class Term_Condition extends JPanel{
    private Labels titre = new Labels("Termes et conditions",Fonts.textFont,Colors.text,25);
    private Labels Mini_titre = new Labels("SMART TEKNOLOJIA", Fonts.textFont, Colors.purple, 20);
    public Term_Condition(){
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(15,10,10,10));
        this.setOpaque(false);
        this.add(titre(),BorderLayout.NORTH);
        this.add(Condition(),BorderLayout.CENTER);
    }
    private JPanel titre(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
       
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panel.add(titre);
        panel.setOpaque(false);

        return panel;

    }
     //Contenu du termes et conditions
     private JPanel Condition(){

        VerticalFlowLayout layout = new VerticalFlowLayout();
        layout.setHorizontalFill(true);

        // *********************** PANELS ************************

        
        JPanel panel = new JPanel();
        JScrollPane Scroll = new JScrollPane();
        JPanel pan = new JPanel();

        panel.setLayout(layout);
        panel.setOpaque(false);
//-----******************* LABELS ************-*--------

       

        JTextArea Term_condition = new JTextArea("");
        Term_condition.setEditable(false);
        Term_condition.setBackground(Colors.backgrounds);
        Scroll.setViewportView(pan);

// ------------------recupération du contenu du pdf dans un textArea-----------
        try 
        {
            //Créer une instance PdfReader.
            PdfReader pdf = new PdfReader("term_Condi.pdf");  
       
            //Récupérer le nombre de pages en pdf.
            int nbrPages = pdf.getNumberOfPages(); 
       
            //Itérer le pdf à travers les pages.
            for(int i=1; i <= nbrPages; i++) 
            { 
                //Extraire le contenu de la page à l'aide de PdfTextExtractor.
                String content = PdfTextExtractor.getTextFromPage(pdf, i);
       
                //Afficher le contenu de la page sur la console.
                // System.out.println( content);
                Term_condition.setText(content);
            }
       
            //Fermez le PdfReader.
            pdf.close();
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        pan.setBackground(Colors.backgrounds);
        pan.add(Term_condition);
        // pan.setSize(500, 700);

        panel.add(Mini_titre);
        panel.add(Scroll);

        return panel;
    }
    public Labels getTitre() {
        return titre;
    }
    public void setTitre(Labels titre) {
        this.titre = titre;
    }
    public Labels getMini_titre() {
        return Mini_titre;
    }
    public void setMini_titre(Labels mini_titre) {
        Mini_titre = mini_titre;
    }
    
}
