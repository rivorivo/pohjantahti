package ohtumini.bibtex;

import viitteet.Viitelista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Simo
 */
public class DataTiedosto {

    private final String DEFAULTNIMI = "viitelista.rflist";
    private String nimi;
    private File tiedosto;

    /**
     * Luo DataTiedosto olion ja asettaa sille nimen "viitelista.rflist"
     * Huom! Ei luo itse tiedostoa.
     */
    public DataTiedosto() {
        this.nimi = DEFAULTNIMI;
    }

    /**
     * Asettaa halutun nimen tulevalle tiedostolle
     * Huom! Ei luo itse tiedostoa.
     * @param tiedostoNimi 
     */
    public DataTiedosto(String tiedostoNimi) {
        this.nimi = tarkastaTiedostoNimi(tiedostoNimi);
    }

    /**
     * Asettaa annetun tiedoston, jota käsitellään
     * @param tiedosto .rflist data tiedosto
     */
    public DataTiedosto(File tiedosto) {
        this.tiedosto = tiedosto;
    }

    /**
     * Tallentaa annetun viitelistan tiedostoon
     * @param viitelista viitelista, joka tallennetaan
     */
    public void tallennaTiedostoon(Viitelista viitelista) {
        try (FileOutputStream s = new FileOutputStream(tiedosto); ObjectOutputStream p = new ObjectOutputStream(s)) {
            p.writeObject(viitelista);
            p.flush();
        } catch (Exception ex) {

        }
    }

    /**
     * Hakee teidostosta Viitelista olion
     * Jos tiedosto ei validi tai ei löydy palauttaa tyhjän viitelistan
     * @return Viitelista tiedostosta 
     */
    public Viitelista haeTiedostosta() {
        try (FileInputStream i = new FileInputStream(tiedosto); ObjectInputStream q = new ObjectInputStream(i)) {
            Viitelista v = (Viitelista) q.readObject();
            return v;
        } catch (Exception ex) {

        }

        return new Viitelista("");
    }

    /**
     * Luo tiedostojärjestelmään tiedoston tallentamista varten
     * @return Jos tiedosto jo olemassa niin false 
     * @throws IOException Jos oikeudet ei riitä
     */
    public boolean luoTiedosto() throws IOException {
        tiedosto = new File(nimi);
        return tiedosto.createNewFile();

    }
    
    /**
     * Asettaa tiedostolle nimen
     * @param nimi Nimi joka tiedostolle asetetaan
     */
    public void asetaNimi(String nimi) {
        this.nimi = tarkastaTiedostoNimi(nimi);
    }

    private String tarkastaTiedostoNimi(String nimi) {
        if (nimi.contains(".rflist")) {
            return nimi;
        } else {
            return nimi + ".rflist";
        }
    }

}
