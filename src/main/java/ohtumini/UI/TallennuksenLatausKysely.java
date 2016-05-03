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
        ArrayList<File> tiedostot = haetiedostot();
        String s = luetteleTiedostot(tiedostot);
        if (!s.isEmpty()) {
            IO.print("Käytettävissä olevat tiedostot:");
            IO.print(s);
            lataaTiedosto(tiedostot);
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

    private void lataaTiedosto(ArrayList<File> tiedosto) {
        IO.print("Mikä tiedosto ladataan?");
        String nimi = IO.readLine("> ");
        File file = haeTiedosto(nimi, tiedosto);
        if (file.exists()) {
            DataTiedosto t = new DataTiedosto(file);
            ladattuViitelista = t.haeTiedostosta();
            if (ladattuViitelista == null) {
                IO.print("Tallennuksen lataus ei onnistunut, tiedosto on virheellinen");
                return;
            }
            IO.print("Tallennuksen lataus onnistui!");
        }
    }

    private File haeTiedosto(String nimi, ArrayList<File> tiedostot) {
        try {
            int i = Integer.parseInt(nimi);
            return tiedostot.get(i - 1);
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

    private String luetteleTiedostot(ArrayList<File> tiedostot) {
        String tuloste = "";
        int i = 1;
        for (File file : tiedostot) {
            tuloste += "[" + i + "] " + file.getName() + " ";
            i++;
        }
        return tuloste;
    }
}
