package services;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import interfaces.Parametre;

public class ParametreService {
    private Parametre parametre = new Parametre();

    public ParametreService(){

        parametre.getList().setSelectedIndex(0);

        parametre.getList().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if(!e.getValueIsAdjusting()){
                    ScannerRFIDService.scanning=false;//scanning RFID 
                    switch(parametre.getList().getSelectedIndex()){
                        case 0:
                            parametre.load(parametre.getApparenceService().getApparence());
                            break;
                        case 1:
                            ScannerRFIDService.scanning=true;
                            parametre.load(parametre.getScannerRFIDService().getScanner());
                            break;
                        case 2:
                            parametre.load(parametre.getAccountService().getAccount());
                            break;
                        case 3:
                            parametre.load(parametre.getExporterService().getExporter());
                            break;
                        case 4:
                        parametre.load(parametre.getAproposService().getApropos());
                            break;
                        case 5:
                            parametre.load(parametre.getTerm_conditionService().getTerm_Condition());
                            break;
                    }
                }
                
            }
            
        });
        
    }

    public Parametre getParametre() {
        return parametre;
    }

    public void setParametre(Parametre parametre) {
        this.parametre = parametre;
    }

    
}
