package ohtumini.UI;

import java.io.IOException;
import ohtumini.bibtex.DataTiedosto;
import ohtumini.io.IO;
import viitteet.Viitelista;

public class TallennusKysely {

    private final IO io;
    private final Viitelista v;

    public TallennusKysely(IO io, Viitelista v) {
        this.io = io;
        this.v = v;
    }

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
                
                String yn = "";
                while (!(yn.startsWith("n") || yn.startsWith("e") || yn.startsWith("y") || yn.startsWith("k"))) {
                    io.print("Tiedosto on jo olemassa. Ylikirjoitetaanko?");
                    yn = io.readLine("Y/n > ").toLowerCase();
                }
                if (yn.toLowerCase().startsWith("n") || yn.toLowerCase().startsWith("e")) {
                    String nimi2 = kyseleNimi();
                    if (nimi.equals(nimi2)) {
                        nimi2 += "1";
                    }
                    t.asetaNimi(nimi2);
                    t.luoTiedosto();
                }
            }
            t.tallennaTiedostoon(v);
        } catch (IOException ex) {
            io.print("Ei voida luoda tiedostoa. Tarkasta kirjoitusoikeudet!");
        }

    }

    private String kyseleNimi() {
        io.print("Anna tiedostonimi mihin tallennetaan (tyhjä default):");
        String nimi = io.readLine("> ");
        return nimi;
    }

}
