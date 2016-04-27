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
public abstract class Viite implements java.io.Serializable {
    /**
     * Tämän viitteen kentät. Jokainen aliluokka asettaa nämä itse.
     */
    protected String[] kentat;
    /**
     * Merkitsee onko kenttä pakollinen vai ei. Jos pakollisuus[i] = true,
     * kentat[i] onn pakollinen.
     */
    protected boolean[] pakollisuus;
    /** 
     * Kenttien arvot     * 
     */
    protected String[] avaimet;
    /**
     * Tämän Viitteen tunniste, jolla tähän viitteeseen viitataan.
     */
    protected String tunniste;
    /**
     * Palauttaa kenttien nimet. Käytä yhdessä lisaaTieto(String kentanNimi, String avain)
     * funktion kanssa.
     * @return Niiden kenttien nimet joista tämä viite pitää kirjaa.
     * 
     */
    public String[] kentat() {
        return kentat;
    }
    
    /**
     * Asettaa Viitteen kentän. Kenttä jota muutetaan on se, jonka nimi vastaa
     * kentanNimeä, ja tämän arvoksi muutetaan avain.
     * @param kentanNimi Kenttä jota muutetaan.
     * @param avain Uusi arvo.
     */
    public void lisaaTieto(String kentanNimi, String avain) {
        for (int i = 0; i < kentat.length; i++) {
            if (kentat[i].compareToIgnoreCase(kentanNimi) == 0) {
                avaimet[i] = avain;
                return;
            }
        }
    }
    
    /**
     * Palauttaa tämän viitteen avaimet listattuna. Järjestys on sama kuin
     * kentat()-metodin palauttamassa listassa.
     * @return Viitteen avaimet String-listassa.
     */
    protected String[] getAvaimet() {
        return avaimet;
    }
    
    /**
     * Lukee avaimen kentästä kentanNimi
     * @param kentanNimi Kentta josta avain luetaan
     * @return kentanNimi-nimisen kentan avaimen arvo.
     */
    public String lueTieto(String kentanNimi) {
        for (int i = 0; i<kentat.length; i++) {
            if (kentat[i].compareToIgnoreCase(kentanNimi) == 0) {
                return avaimet[i];
            }
        }
        return null;
    }
    
    /**
     * Palauttaa true mikäli kentanNimi-niminen kenttä on pakollinen.
     * Mikäli kenttää ei määritelty, se ei ole pakollinen.
     * @param kentanNimi
     * @return 
     */
    public boolean onkoPakollinen(String kentanNimi) {
        for (int i = 0; i<kentat.length; i++) {
            if (kentat[i].compareToIgnoreCase(kentanNimi) == 0) {
                if (pakollisuus[i]) return true;
            }
        }
        return false;
    }
    
    /**
     * Kertoo onko tällä viitteellä kenttää nimellä kentanNimi.
     * @param kentanNimi Haettava kenttä.
     * @return Palauttaa true jos kentta nimeltä kentanNimi on olemassa, muutoin false.
     */
    public boolean onkoKenttaOlemassa(String kentanNimi) {
        for (int i = 0; i<kentat.length; i++) {
            if (kentat[i].compareToIgnoreCase(kentanNimi) == 0) {
                return true;
            }
        }
        return false;
    }
        
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
        palautus = "@" + annaViitteenTyypinNimi() + "{" + getTunniste() + ", \n";
        for (String kentat1 : kentat()) {
            if (lueTieto(kentat1) != null) {
                palautus += kentat1 + " = {" + lueTieto(kentat1) + "}, \n"; 
            }
        }        
        palautus += "}";
        
        palautus = skanditBibteXiksi(palautus);
        return palautus;
    }
    
    private String skanditBibteXiksi(String s) {
        s = s.replace("ä", "\\\"{a}");
        s = s.replace("ö", "\\\"{o}");
        s = s.replace("Ä", "\\\"{A}");
        s = s.replace("Ö", "\\\"{O}");
        s = s.replace("å", "{\\aa}");
        s = s.replace("Å", "{\\AA}");
        return s;
    }
    
    /**
     * Palauttaa tämän viitteen tyypin String-tietona. Esimerkiksi "Article".
     * @return 
     */
    public abstract String annaViitteenTyypinNimi();
    
    /**
     * Palauttaa tämän Viitteen BibTeX-tunnisteen.
     * @return 
     */
    public String getTunniste() {
        return tunniste;
    }
    /**
     * Asettaa tämän Viitteen BibTex-tunnisteen.
     * @param tunniste 
     */
    public void setTunniste(String tunniste) {
        this.tunniste = tunniste;
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
