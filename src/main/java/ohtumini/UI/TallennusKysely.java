package ohtumini.UI;

import java.io.IOException;
import ohtumini.bibtex.DataTiedosto;
import ohtumini.io.IO;
import viitteet.Viitelista;

/**
 *
 * @author Simo
 */
public class TallennusKysely {

    private final IO IO;
    private final Viitelista VIITELISTA;

    /**
     * Luo uuden ohjelman tilan tallentamis kyselyn
     * Ohjelma ei tällähetkellä tallenna 
     * BibTex tulostettavaksi lisättyjä viitteitä
     * @param io 
     * @param v Viitelista, joka tallennetaan tiedostoon
     */
    public TallennusKysely(IO io, Viitelista v) {
        this.IO = io;
        this.VIITELISTA = v;
    }

    /**
     * Käynnistää tallennus kyselyn
     */
    public void suorita() {
        String nimi = kyseleNimi();
        DataTiedosto t;
        if (nimi.isEmpty()) {
            t = new DataTiedosto();
        } else {
            t = new DataTiedosto(nimi);
        }
        try {
            if (!t.luoTiedosto()) {
                if (!varmistus("Tiedosto on jo olemassa. Ylikirjoitetaanko?")) {
                    String nimi2 = kyseleNimi();
                    if (nimi.equals(nimi2)) {
                        IO.print("Tallennus ei onnistunut. Epäkelpo tiedostonimi.");
                        return;
                    } 
                    t.asetaNimi(nimi2);
                    t.luoTiedosto();
                }
            }
            t.tallennaTiedostoon(VIITELISTA);
            IO.print("Tallennus onnistui!");
        } catch (IOException ex) {
            IO.print("Ei voida luoda tiedostoa. Tarkasta kirjoitusoikeudet!");
        }

    }


    private String kyseleNimi() {
        IO.print("Anna tiedostonimi mihin tallennetaan (tyhjä default):");
        String nimi = IO.readLine("> ");
        return nimi;
    }

    private boolean varmistus(String kysymys) {
        String yn = "";
        while (!(yn.startsWith("n") || yn.startsWith("e") || yn.startsWith("y") || yn.startsWith("k"))) {
            IO.print(kysymys);
            yn = IO.readLine("y/n > ").toLowerCase();
        }
        return (yn.startsWith("y") || yn.startsWith("k"));
    }

}
