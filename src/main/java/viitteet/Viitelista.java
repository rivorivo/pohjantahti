package viitteet;

import java.util.ArrayList;
import viitteet.Viite;

public class Viitelista implements java.io.Serializable {
    
    private String nimi;
    private ArrayList<Viite> viitteet;

    public Viitelista(String nimi) {
        this.nimi = nimi;
        viitteet = new ArrayList<>();
    }
    
    public void lisaaViiteListaan(Viite v) {
        viitteet.add(v);
    }
    
    public boolean poistaViiteListasta() {
        return true;
    }

   

    public String getNimi() {
        return nimi;
    }

    public ArrayList<Viite> getViitteet() {
        return viitteet;
    }
    
    

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    
    
}
