/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * @param tiedostoNimi tiedostolle annettu nimi
     * @throws IOException antaa keskeytyksen jos tiedoston luominen ei onnistu
     */
    public BibTexTiedosto(String tiedostoNimi) throws IOException {
        this.tiedostoNimi = tarkastaTiedostoNimi(tiedostoNimi);
        luoTiedostoJosEiOlemassa();
    }

    /**
     * Lisää viitteen tiedoston loppuun
     * @param viite viite BibTex muodossa
     */
    public void lisaaViiteTiedostoon(String viite) {
        BufferedWriter fw;
        try {
            fw = new BufferedWriter(new FileWriter(tiedosto, true));
            fw.write(viite);
            fw.newLine();
            fw.newLine();
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            
        }

    }
    
    /**
     * Poistaa BibTextiä varten luodun tiedoston
     * @return Palauttaa true jos onnistui
     */
    public boolean poistaTiedosto() {
        return tiedosto.delete();
    }

    private void luoTiedostoJosEiOlemassa() throws IOException {
        tiedosto = new File(tiedostoNimi);
        if (!tiedosto.exists()) {
            tiedosto.createNewFile();
        }
    }
    
    private String tarkastaTiedostoNimi(String nimi) {
        if (nimi.contains(".bib")) {
            return nimi;
        } else {
            return nimi + ".bib";
        }
    }

}
