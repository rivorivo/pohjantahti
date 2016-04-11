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
    @Override
    /**
     * Printtaa BibTeX-formaatissa viitteen tiedot.
     */
    public abstract String toString();    
}
