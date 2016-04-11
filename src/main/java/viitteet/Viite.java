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
    public abstract String luoBibTeX();
    @Override
    public abstract String toString();    
}
