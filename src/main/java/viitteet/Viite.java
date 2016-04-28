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
     * Kenttien arvot *
     */
    protected String[] avaimet;
    /**
     * Monesko vaihtoehtoisista kentistä tämä kenttä on. Esimerkiksi kenttä
     * Author/Editor voi olla Author tai Editor. Jos vastaava
     * kenttaIndeksit-kohta on 0, tämä tulkitaan Author-kentäksi. Jos
     * kenttaIndeksit-kohta on 1, tämä tulkitaan Editor-kentäksi.
     */
    protected int[] kenttaIndeksit;
    /**
     * Tämän Viitteen tunniste, jolla tähän viitteeseen viitataan.
     */
    protected String tunniste;

    /**
     * Palauttaa kenttien nimet. Käytä yhdessä lisaaTieto(String kentanNimi,
     * String avain) funktion kanssa.
     *
     * @return Niiden kenttien nimet joista tämä viite pitää kirjaa.
     *
     */
    public String[] kentat() {
        return kentat;
    }

    public String[] valitutKentat() {
        String[] palautus = new String[kentat.length];
        for (int i = 0; i < kentat.length; i++) {
            palautus[i] = kentat()[i].split("/")[kenttaIndeksit[i]];
        }
        return palautus;
    }

    /**
     * Asettaa Viitteen kentän. Kenttä jota muutetaan on se, jonka nimi vastaa
     * kentanNimeä, ja tämän arvoksi muutetaan avain.
     *
     * @param kentanNimi Kenttä jota muutetaan.
     * @param avain Uusi arvo.
     */
    public void lisaaTieto(String kentanNimi, String avain) {
        for (int i = 0; i < kentat.length; i++) {
            if (moneskoKentta(kentat[i], kentanNimi) == kentat[i].split("/").length) {
                avaimet[i] = avain;
                return;
            }
            if (moneskoKentta(kentat[i], kentanNimi) >= 0) {
                avaimet[i] = avain;
                kenttaIndeksit[i] = moneskoKentta(kentat[i], kentanNimi);
                return;
            }
        }
    }

    /**
     * Palauttaa tämän viitteen avaimet listattuna. Järjestys on sama kuin
     * kentat()-metodin palauttamassa listassa.
     *
     * @return Viitteen avaimet String-listassa.
     */
    protected String[] getAvaimet() {
        return avaimet;
    }

    /**
     * Lukee avaimen kentästä kentanNimi
     *
     * @param kentanNimi Kentta josta avain luetaan
     * @return kentanNimi-nimisen kentan avaimen arvo.
     */
    public String lueTieto(String kentanNimi) {
        int i = onkoKentta(kentanNimi);
        if (i == -1) return null;
        
        //tarkistetaan onko kyse samasta kentästä, Author/Editor kentän kysely
        //Editor-kentällä, kun Author-kenttä on asetettu, tulee palauttaa null.
        if (moneskoKentta(kentat[i], kentanNimi) < kentat[i].split("/").length) {
            if (moneskoKentta(kentat[i], kentanNimi) != kenttaIndeksit[i]) {
                return null;
            }
        }
        return avaimet[i];
    }
    
    /**
     * palauttaa kentan indeksin kentat-taulukossa mikäli String vastaa kenttää.
     * Mikäli string ei vastaa kenttää, palautetaan -1. Esimerkiksi book-luokalla
     * onkoKentta("Author/Editor"), onkoKentta("Author") ja onkoKentta("Editor")
     * palauttavat 0.
     * @param kentanNimi
     * @return 
     */
    private int onkoKentta(String kentanNimi) {
        for (int i = 0; i < kentat.length; i++) {
            if (moneskoKentta(kentat[i], kentanNimi) >= 0) return i;
        }
        return -1;
    }
    
    /**
     * Palauttaa monesko "/"-merkillä erotettu osajono verrattava on joukosta kentta.
     * 
     * @param kentta Haluttu kenttä johon verrataan, esim "Author/Editor".
     * @param verrattava String joka ehkä mätsää kentän kanssa
     * @return -1 jos ei mätsää, numero joka vastaa osuvaa alijonoa mikäli alijonomätsi,
     * tai "/"-merkillä eroteltujen alijonojen määrä jos esimerkiksi 
     * kentta = "Author/Editor" ja verrattava = "Author/Editor".
     */
    private int moneskoKentta(String kentta, String verrattava) {
        for (int i = 0; i < kentta.split("/").length; i++) {
            if (kentta.split("/")[i].compareToIgnoreCase(verrattava) == 0) {
                return i;
            }
        }        
        if (kentta.compareToIgnoreCase(verrattava) == 0) {
            return kentta.split("/").length;
        }
        return -1;
    }

    /**
     * Palauttaa true mikäli kentanNimi-niminen kenttä on pakollinen. Mikäli
     * kenttää ei määritelty, se ei ole pakollinen.
     *
     * @param kentanNimi
     * @return
     */
    public boolean onkoPakollinen(String kentanNimi) {
        int i = onkoKentta(kentanNimi);
        if (i == -1) return false;
        if (pakollisuus[i]) return true;
        return false;
    }

    /**
     * Kertoo onko tällä viitteellä kenttää nimellä kentanNimi.
     *
     * @param kentanNimi Haettava kenttä.
     * @return Palauttaa true jos kentta nimeltä kentanNimi on olemassa, muutoin
     * false.
     */
    public boolean onkoKenttaOlemassa(String kentanNimi) {
        return !(onkoKentta(kentanNimi) == -1);
    }

    /**
     * Palauttaa BibTeX-formaatissa viitteen tiedot. Esimerkiksi:
     *
     * author = "Joku", title = "Joku muu", publisher = "Ihan joku kolmas", year
     * = 1975
     *
     * @return Tämän olion kentät ja niiden sisällöt BibTeX-yhteensopivassa
     * muodossa.
     */
    public String luoBibTeX() {
        String palautus;
        palautus = "@" + annaViitteenTyypinNimi() + "{" + getTunniste() + ", \n";
        for (String kentta : valitutKentat()) {
            if (lueTieto(kentta) != null) {
                palautus += kentta + " = {" + lueTieto(kentta) + "}, \n";
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
     *
     * @return
     */
    public abstract String annaViitteenTyypinNimi();

    /**
     * Palauttaa tämän Viitteen BibTeX-tunnisteen.
     *
     * @return
     */
    public String getTunniste() {
        return tunniste;
    }

    /**
     * Asettaa tämän Viitteen BibTex-tunnisteen.
     *
     * @param tunniste
     */
    public void setTunniste(String tunniste) {
        this.tunniste = tunniste;
    }

    @Override
    public String toString() {
        String palautus = "";
        for (int i = 0; i < kentat.length; i++) {
            palautus += kentat[i] + ": " + ((avaimet[i] == null) ? "Not set" : avaimet[i]) + "\n";
        }
        return palautus;
    }
}
