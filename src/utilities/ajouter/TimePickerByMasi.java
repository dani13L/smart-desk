package utilities.ajouter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.intellij.openapi.ui.VerticalFlowLayout;

import utilities.Buttons;
import utilities.Colors;
import utilities.Fonts;
import utilities.Labels;


public class TimePickerByMasi extends JPanel{
	
	private Labels titre = new Labels("Choisir l'heure",Fonts.textFont, Colors.text,23);
	private Labels heure,minute,seconde;
	@SuppressWarnings("rawtypes")
	private JComboBox cHeure,cMinute,cSeconde;
	private Buttons choisir,annuler ;
	
	private JPanel panelHeure = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.LEFT,0,15,true,false));
	private JPanel panelMinute = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.LEFT,0,15,true,false));
	private JPanel panelSeconde = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.LEFT,0,15,true,false));
	
	private JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelCombo  = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private JPanel panelTitre = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private boolean entered = false;
	
	public TimePickerByMasi() {
		buildUI();
	}
	
	public JComboBox getcHeure() {
		return cHeure;
	}

	public void setcHeure(JComboBox cHeure) {
		this.cHeure = cHeure;
	}

	public JComboBox getcMinute() {
		return cMinute;
	}

	public void setcMinute(JComboBox cMinute) {
		this.cMinute = cMinute;
	}

	public JComboBox getcSeconde() {
		return cSeconde;
	}

	public void setcSeconde(JComboBox cSeconde) {
		this.cSeconde = cSeconde;
	}

	public Buttons getChoisir() {
		return choisir;
	}

	public void setChoisir(Buttons choisir) {
		this.choisir = choisir;
	}

	public Buttons getAnnuler() {
		return annuler;
	}

	public void setAnnuler(Buttons annuler) {
		this.annuler = annuler;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void buildUI() {
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		setBackground(Color.white);
		
		//New Labels
		heure = new Labels("Heure",Fonts.textFont, Colors.text,15);
		minute = new Labels("Minute",Fonts.textFont, Colors.text,15);
		seconde = new Labels("Seconde",Fonts.textFont, Colors.text,15);
		
		//New ComboBox
		this.setupComboBox();
		
		//New Buttons PERSONNALISEE
		choisir = new Buttons("Choisir");
		annuler = new Buttons("Annuler");
		
		
		//Add panel Vertical
		panelHeure.add(heure);panelHeure.add(cHeure);panelHeure.setOpaque(false);
		panelMinute.add(minute);panelMinute.add(cMinute);panelMinute.setOpaque(false);
		panelSeconde.add(seconde);panelSeconde.add(cSeconde);panelSeconde.setOpaque(false);
		
		//Add panel combo
		panelCombo.add(panelHeure);panelCombo.add(Box.createHorizontalStrut(10));
		panelCombo.add(panelMinute);panelCombo.add(Box.createHorizontalStrut(10));
		panelCombo.add(panelSeconde);
		panelCombo.setOpaque(false);
		panelCombo.setBorder(new FlatLineBorder(new Insets(1,20,1,10), getBackground()));
		
		//Add panel Buttons
		
		panelButton.add(choisir);
		panelButton.add(Box.createHorizontalStrut(3));
		panelButton.add(annuler);
		panelButton.setOpaque(false);
		panelButton.setBorder(new FlatLineBorder(new Insets(0,0,0,25), getBackground()));
		
		//Add panel titre
		titre.setBorder(new FlatLineBorder(new Insets(10,20,2,10), getBackground()));
		panelTitre.add(titre);
		panelTitre.setOpaque(false);
		
		//Add layout
		add(panelTitre);
		add(Box.createVerticalStrut(10));
		add(panelCombo);
		add(Box.createVerticalStrut(30));
		add(panelButton);
	}
	@SuppressWarnings("unused")
	private void setupComboBox() {
		cHeure = new JComboBox();cHeure.setEditable(true);cHeure.setMaximumRowCount(5);
		cMinute = new JComboBox();cMinute.setEditable(true);cMinute.setMaximumRowCount(5);
		cSeconde = new JComboBox();cSeconde.setEditable(true);cSeconde.setMaximumRowCount(5);
		
		cHeure.setRenderer(new ComboCellRenderer());
		cHeure.setEditor(new ComboEditor());
		cMinute.setRenderer(new ComboCellRenderer());
		cMinute.setEditor(new ComboEditor());
		cSeconde.setRenderer(new ComboCellRenderer());
		cSeconde.setEditor(new ComboEditor());
		
		
		//Add elements to the combo boxes 
		for(int i = 0;i<24;i++) {
			if(i<10) {
				cHeure.addItem("0"+i);
			}else {
				cHeure.addItem(i);
			}
		}
		
		for(int i = 0;i<60;i++) {
			if(i<10) {
				cMinute.addItem("0"+i);
				cSeconde.addItem("0"+i);
			}else {
				cMinute.addItem(i);
				cSeconde.addItem(i);
			}
		}
		
		cHeure.setPreferredSize(new Dimension(80,30));
		cMinute.setPreferredSize(new Dimension(80,30));
		cSeconde.setPreferredSize(new Dimension(80,30));
	}

}
