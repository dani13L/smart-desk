package tasks;

import javax.swing.JFrame;

public class DataModifiable {

    // Information personnel
    public static String nom="";
    public static String prenom="";
    public static long ID;
    public static String photo = "";
    public static String poste = "";

    //Heure de service
    public static String debut = "";
    public static String pause = "";
    public static String reprise = "";
    public static String fin = "";

    // Carte rfid
    public static String carte = "";
    public static int id=0;

    // Autorisation de modifier
    public static boolean modifiable = false;
    public static boolean nouveau_data = false;
    public static JFrame frame;
    
}
