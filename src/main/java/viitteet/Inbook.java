/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viitteet;

/**
 *
 * @author samukaup
 */
public class Inbook extends Viite {
    /**
     * Kentät jotka tämä viitetyyppi muistaa, nimettyinä.
     */
    public static final String[] kentat = {"Author/Editor", "Title", "Chapter","Publisher", "Year", "Pages", "Volume/Number", "Series", "Type", "Address","Edition", "Month", "Note", "Key"};
    /**
     * Merkitsee onko vastaava kentta pakollinen. True, jos on pakollinen, false jos ei pakollinen.
     * kentat[0] on pakollinen mikäli pakollisuus[0] on true.
     */
    private static final boolean[] pakollisuus = {true, true, true, true, true, true, false, false, false, false, false, false, false, false};
    
    /**
     * Luo uuden Kirjaviitteen.
     */
    public Inbook() {
        avaimet = new String[kentat.length];
        super.pakollisuus = pakollisuus;
        super.kentat = kentat;
        super.kenttaIndeksit = new int[kentat.length];

    }

    @Override
    public String annaViitteenTyypinNimi() {
        return "Inbook";
    }
}
