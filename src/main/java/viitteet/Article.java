/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viitteet;

/**
 *
 * @author jphanski
 */
public class Article extends Viite{
    private static final String[] kentat = {"Author", "Title", "Journal", "Year", "Volume", "Number", "Pages", "Month", "Note", "Key"};
    private static final boolean[] pakollisuus = {true, true, true, true, true, false, false, false, false, false};
    private String[] avaimet;
    
    public Article() {
        avaimet = new String[kentat.length];
    }
    
    @Override
    public String[] kentat() {
        return kentat;
    }

    public String[] getAvaimet() {
        return avaimet;
    }

    @Override
    public void lisaaTieto(String kentanNimi, String avain) {
        for (int i = 0; i < kentat.length; i++) {
            if (kentat[i].compareToIgnoreCase(kentanNimi) == 0) {
                avaimet[i] = avain;
                return;
            }
        }
    }

    @Override
    public String toString() {
        String palautus = "";
        for (int i = 0; i<kentat.length; i++) {
            palautus += kentat[i] + ": " + ((avaimet[i] == null) ? "Not set" : avaimet[i]) + "\n";           
        }
        return palautus;
    }

    @Override
    public boolean onkoPakollinen(String kentanNimi) {
        for (int i = 0; i<kentat.length; i++) {
            if (kentat[i].compareToIgnoreCase(kentanNimi) == 0) {
                if (pakollisuus[i]) return true;
            }
        }
        return false;
    }

    @Override
    public String lueTieto(String kentanNimi) {
        for (int i = 0; i<kentat.length; i++) {
            if (kentat[i].compareTo(kentanNimi) == 0) {
                return avaimet[i];
            }
        }
        return null;
    }
    
}
