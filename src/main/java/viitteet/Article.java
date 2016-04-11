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
    private static final String[] kentat = {"Author", "Title", "Journal", "Year", "Volume"};
    private String[] avaimet;
    
    public Article() {
        avaimet = new String[kentat.length];
    }
    
    @Override
    public String[] kentat() {
        return kentat;
    }

    @Override
    public void lisaaTieto(String kentanNimi, String avain) {
        for (int i = 0; i < kentat.length; i++) {
            if (kentat[i].compareTo(kentanNimi) == 0) {
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
    
}
