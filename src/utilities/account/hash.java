package utilities.account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash {
    public char pass[];
    public hash(char pass[]){
        this.pass=pass;
    }
     // Fonction qui fait le hackage de mot de passe
     public  byte[] hasher() throws NoSuchAlgorithmException{
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        byte[] passwordHash = md.digest(new String(this.pass).getBytes(StandardCharsets.UTF_8));
        System.out.println("hashage effectué");
        return passwordHash;
    }
}
