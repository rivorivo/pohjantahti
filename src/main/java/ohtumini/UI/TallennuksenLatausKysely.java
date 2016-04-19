package ohtumini.UI;

import java.io.File;
import java.util.ArrayList;
import ohtumini.bibtex.DataTiedosto;
import ohtumini.io.IO;
import viitteet.Viitelista;

public class TallennuksenLatausKysely {

    private final IO io;
    private Viitelista ladattuViitelista;

    public TallennuksenLatausKysely(IO io) {
        this.io = io;
    }

    public void suorita() {
        ArrayList<File> f = haetiedostot();
        String s = luetteleTiedostot(f);
        if (!s.isEmpty()) {
            io.print("Käytettävissä olevat tiedostot:");
            io.print(s);
            lataaTiedosto(f);
        } else {
            io.print("Kansiossa ei ole tiedostoja joita ladata!");
        }
    }

    private void lataaTiedosto(ArrayList<File> fi) {
        io.print("Mikä tiedosto ladataan?");
        String nimi = io.readLine("> ");
        File f = haeTiedosto(nimi, fi);
        if (f.exists()) {
            DataTiedosto t = new DataTiedosto(f);
            ladattuViitelista = t.haeTiedostosta();
            io.print("Tallennuksen lataus onnistui!");
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
        File folder = new File(".");
        ArrayList<File> f = new ArrayList<>();
        for (File listFile : folder.listFiles()) {
            if (listFile.isFile() && listFile.getName().contains(".rflist")) {
                f.add(listFile);
            }
        }
        return f;
    }

    private String luetteleTiedostot(ArrayList<File> files) {
        String s = "";
        int i = 1;
        for (File file : files) {
            s += "[" + i + "] " + file.getName() + " ";
            i++;
        }
        return s;
    }

    public Viitelista getLadattuViitelista() {
        return ladattuViitelista;
    }

}
