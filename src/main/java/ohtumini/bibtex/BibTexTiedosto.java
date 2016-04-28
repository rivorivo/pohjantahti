package ohtumini.bibtex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Simo
 */
public class BibTexTiedosto {

    private final String tiedostoNimi;
    private File tiedosto;

    /**
     * Luo uuden tiedoston, johon viitteet tulostetaan BibTex muodossa
     *
     * @param tiedostoNimi tiedostolle annettu nimi
     */
    public BibTexTiedosto(String tiedostoNimi) {
        this.tiedostoNimi = tarkastaTiedostoNimi(tiedostoNimi);
    }

    /**
     * Lisää viitteen tiedoston loppuun
     *
     * @param viite viite BibTex muodossa
     * @return true jos tiedostoon kirjoitus onnistuu
     */
    public boolean lisaaViiteTiedostoon(String viite) {
        BufferedWriter fw;
        try {
            fw = new BufferedWriter(new FileWriter(tiedosto, true));
            fw.write(viite);
            fw.newLine();
            fw.newLine();
            fw.flush();
            fw.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Poistaa BibTextiä varten luodun tiedoston
     *
     * @return Palauttaa true jos onnistui
     */
    public boolean poistaTiedosto() {
        return tiedosto.delete();
    }

    /**
     * Palauttaa BibText tiedostonimen
     *
     * @return BibText tiedostonimi
     */
    public String getTiedostoNimi() {
        return tiedostoNimi;
    }

    /**
     * Luo tiedoston levylle 
     * @return true, jos tiedoston luodaan
     * @throws java.io.IOException Jos kirjoitusoikeudet eivät riitä
     */
    public boolean luoTiedosto() throws IOException {
        tiedosto = new File(tiedostoNimi);
        return tiedosto.createNewFile();
    }

    private String tarkastaTiedostoNimi(String nimi) {
        if (nimi.contains(".bib")) {
            return nimi;
        } else {
            return nimi + ".bib";
        }
    }
}
