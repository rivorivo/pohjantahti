package ohtumini;

import java.io.IOException;
import ohtumini.io.*;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import ohtumini.bibtex.BibTexTiedosto;
import viitteet.*;

public class App {

    private IO io;
    private LinkedList<Viite> viitteet;

    public App(IO io) {
        this.io = io;
        viitteet = new LinkedList<>();
    }

    //Siivottava? Malli alla tai ehdotuksia?
    public void run() {
        String komento;
        String komentoNoCapitalizationChanges;
        while (true) {
            komentoNoCapitalizationChanges = io.readLine(">");
            komento = komentoNoCapitalizationChanges.toLowerCase(Locale.ROOT);
            if (komento.startsWith("luo-viite")) {                  //luo-viite 
                luoUusiViite();
            } else if (komento.startsWith("tulosta-viite")) {       //tulosta-viite <viitteen numero>
                int viitteenNumero = Integer.parseInt(komento.substring(14)) - 1;
                io.print("= ");
                io.print("" + viitteet.get(viitteenNumero));
            } else if (komento.startsWith("aseta-kentta")) {        // aseta-kentta <viitteen numero> <kentan nimi> <arvo>
                int viitteenNumero = Integer.parseInt(komento.split(" ")[1]) - 1;
                viitteet.get(viitteenNumero).lisaaTieto(komento.split(" ")[2], komentoNoCapitalizationChanges.split(" ", 4)[3]);
                io.print("= ");
            } else if (komento.startsWith("tulosta-bibtex")) {        // tulosta-bibtex <viitteen numero>
                int viitteenNumero = Integer.parseInt(komento.split(" ")[1]) - 1;
                io.print("= ");
                io.print(viitteet.get(viitteenNumero).luoBibTeX());
            } else if (komento.startsWith("luo-bibtex-tiedosto")) {        // luo bibtext tiedoston viitteistä
                tulostaViitteetTiedostoon();
            } else if (komento.startsWith("lopeta")) {        // luo bibtext tiedoston viitteistä
                break;
            } else {
                io.print("? ");
            }
        }
    }

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        new App(io).run();
    }

    private void luoUusiViite() {
        io.print("Anna viitteen tyyppi");
        String komento = io.readLine("> ");
        if (komento.startsWith("article")) {
            io.print("= ");
            viitteet.add(new Article());
            io.print("" + viitteet.size());
        } else if (komento.startsWith("book")) {
            io.print("= ");
            viitteet.add(new Book());
            io.print("" + viitteet.size());
        }else if (komento.startsWith("palaa")) {
            return;
        } else {
            io.readLine("? ");
        }
    }

    private void tulostaViitteetTiedostoon() {
        io.print("Anna tiedostonimi mihin BibTex tulostetaan:");
        try {
            String nimi = io.readLine("> ");
            BibTexTiedosto t = new BibTexTiedosto(nimi);
            for (Viite v : viitteet) {
                t.lisaaViiteTiedostoon(v.luoBibTeX());
            }
            io.print("Viitteet löytyvät tiedostosta " + nimi + ".bib");
        } catch (IOException ex) {
            io.print("Tiedoston luominen ei onnistu.");
            io.print("Tarkista kansion kirjoitus oikeudet.");
        }
    }
}
