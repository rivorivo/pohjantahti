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
public abstract class Viite {
    /**
     * Palauttaa kenttien nimet. Käytä yhdessä lisaaTieto(String kentanNimi, String avain)
     * funktion kanssa.
     * @return Niiden kenttien nimet joista tämä viite pitää kirjaa.
     * 
     */
    public abstract String[] kentat();
    
    /**
     * Asettaa Viitteen kentän. Kenttä jota muutetaan on se, jonka nimi vastaa
     * kentanNimeä, ja tämän arvoksi muutetaan avain.
     * @param kentanNimi Kenttä jota muutetaan.
     * @param avain Uusi arvo.
     */
    public abstract void lisaaTieto(String kentanNimi, String avain);
    
    /**
     * Lukee avaimen kentästä kentanNimi
     * @param kentanNimi Kentta josta avain luetaan
     * @return kentanNimi-nimisen kentan avaimen arvo.
     */
    public abstract String lueTieto(String kentanNimi);
    
    /**
     * Palauttaa true mikäli kentanNimi-niminen kenttä on pakollinen.
     * Mikäli kenttää ei määritelty, se ei ole pakollinen.
     * @param kentanNimi
     * @return 
     */
    public abstract boolean onkoPakollinen(String kentanNimi);
        
    /**
     * Palauttaa BibTeX-formaatissa viitteen tiedot. Esimerkiksi:
     * 
     * author    = "Joku",
     * title     = "Joku muu",
     * publisher = "Ihan joku kolmas",
     * year      = 1975
     * 
     * @return Tämän olion kentät ja niiden sisällöt BibTeX-yhteensopivassa muodossa. 
     */
    public String luoBibTeX() {
        String palautus;
        palautus = "@" + annaViitteenTyypinNimi() + "{" + getTunniste() + "\n";
        for (int i = 0; i<kentat().length; i++) {
            if (lueTieto(kentat()[i]) != null) {
                palautus += kentat()[i] + ": \t" + lueTieto(kentat()[i]) + "\n"; 
            }
        }
        palautus += "}";
        return palautus;
    }
    public abstract String annaViitteenTyypinNimi();
    public abstract String getTunniste();
    public abstract void setTunniste(String tunniste);
    @Override
    public abstract String toString();    
}
