package services;

import interfaces.Apropos;

public class AproposService {
    private Apropos apropos=new Apropos();
    public AproposService(){

    }
    public Apropos getApropos() {
        return apropos;
    }
    public void setApropos(Apropos apropos) {
        this.apropos = apropos;
    }
    
}
