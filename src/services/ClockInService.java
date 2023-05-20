package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import interfaces.ClockIn;
import interfaces.Template;

public class ClockInService {

    private ClockIn clock_in = new ClockIn();
    

    public ClockInService(){

        

    }

    // Pour charger la liste
    private void load(){
        
    }

    // pour actualiser la liste
    private void refresh(){

    }


    // Accesseurs
    public ClockIn getClock_in() {
        return clock_in;
    }

    public void setClock_in(ClockIn clock_in) {
        this.clock_in = clock_in;
    }
    
}
