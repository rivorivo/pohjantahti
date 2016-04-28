package ohtumini.UI;

import java.io.File;
import java.util.ArrayList;
import ohtumini.bibtex.DataTiedosto;
import ohtumini.io.IO;
import viitteet.Viitelista;

/**
 *
 * @author Simo
 */
public class TallennuksenLatausKysely {

    private final IO IO;
    private Viitelista ladattuViitelista;

    /**
     * Luo uuden latauskyselyn
     *
     * @param io
     */
    public TallennuksenLatausKysely(IO io) {
        this.IO = io;
    }

    /**
     * Käynnistää latauskyselyn
     */
    public void suorita() {
        ArrayList<File> f = haetiedostot();
        String s = luetteleTiedostot(f);
        if (!s.isEmpty()) {
            IO.print("Käytettävissä olevat tiedostot:");
            IO.print(s);
            lataaTiedosto(f);
        } else {
            IO.print("Kansiossa ei ole tiedostoja joita ladata!");
        }
    }

    /**
     * 
     * @return palauttaa Viitelistan, joka ladattiin tiedostosta
     */
    public Viitelista getLadattuViitelista() {
        return ladattuViitelista;
    }

    private void lataaTiedosto(ArrayList<File> fi) {
        IO.print("Mikä tiedosto ladataan?");
        String nimi = IO.readLine("> ");
        File f = haeTiedosto(nimi, fi);
        if (f.exists()) {
            DataTiedosto t = new DataTiedosto(f);
            ladattuViitelista = t.haeTiedostosta();
            IO.print("Tallennuksen lataus onnistui!");
        }
    }

    private File haeTiedosto(String nimi, ArrayList<File> f) {
        try {
            int i = Integer.parseInt(nimi);
            return f.get(i - 1);
        } catch (Exception ex) {
            if (!nimi.contains(".rflist")) {
                nimi += ".rflist";
            }
            return new File(nimi);
        }

    }

    private ArrayList<File> haetiedostot() {
        File kansio = new File(".");
        ArrayList<File> tiedosto = new ArrayList<>();
        for (File listFile : kansio.listFiles()) {
            if (listFile.isFile() && listFile.getName().contains(".rflist")) {
                tiedosto.add(listFile);
            }
        }
        return tiedosto;
    }

    private String luetteleTiedostot(ArrayList<File> files) {
        String tuloste = "";
        int i = 1;
        for (File file : files) {
            tuloste += "[" + i + "] " + file.getName() + " ";
            i++;
        }
        return tuloste;
    }
}
